package com.example.libraryapi.service;

import com.example.libraryapi.dto.ReservationDto;
import com.example.libraryapi.dto.ReservationItemDto;
import com.example.libraryapi.mapper.ReservationMapper;
import com.example.libraryapi.model.Reservation;
import com.example.libraryapi.model.ReservationItem;
import com.example.libraryapi.repository.ReservationItemRepository;
import com.example.libraryapi.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationItemRepository reservationItemRepository;
    private final ReservationMapper reservationMapper;


    public ReservationDto getReservation(UUID id) {
        return reservationMapper.map(reservationRepository.findById(id).orElse(null));
    }



    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(reservationMapper::map)
                .toList();
    }

    @Transactional
    public void addReservation(ReservationDto reservationDto) {
        boolean isBookAvailable = this.areBooksAvailable(reservationDto);
        if (isBookAvailable) {
            Reservation reservation = reservationMapper.map(reservationDto, null);
            reservation.addReservationItem();
            reservationRepository.save(reservation);
        }
    }

    public boolean areBooksAvailable(ReservationDto reservationDto) {
        return reservationDto.getReservationItems()
                .stream()
                .allMatch(reservationItemDto -> {
                    List<ReservationItem> reservations = reservationItemRepository
                            .findByReservationCanceledFalseAndReservationReturnedFalseAndReservationEndOfReservationAfterAndBookId(
                                    LocalDate.now(), reservationItemDto.getBook().getId());
                    int numberOfReservedBooks = reservations.stream()
                            .map(ReservationItem::getQuantity)
                            .reduce(0, Integer::sum);
                    return reservationItemDto.getBook().getNumberOfBooks() - numberOfReservedBooks >= reservationItemDto.getQuantity();
                });
    }
}
