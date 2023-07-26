package com.example.libraryapi.service;

import com.example.libraryapi.dto.ReservationDto;
import com.example.libraryapi.mapper.ReservationMapper;
import com.example.libraryapi.model.Reservation;
import com.example.libraryapi.model.ReservationItem;
import com.example.libraryapi.repository.ReservationItemRepository;
import com.example.libraryapi.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
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



    public List<ReservationDto> getAllRecordsFromReservation() {
        return reservationRepository.findAll()
                .stream()
                .map(reservationMapper::map)
                .toList();
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "books_cache", allEntries = true),
                    @CacheEvict(value = "book_cache", allEntries = true),
                    @CacheEvict(value = "books_by_category_cache", allEntries = true),
                    @CacheEvict(value = "books_by_phrase_cache", allEntries = true),
                    @CacheEvict(value = "books_by_greater_id_cache", allEntries = true),
            }
    )
    @Transactional
    public void addReservation(ReservationDto reservationDto) {
        boolean isBookAvailable = this.areBooksAvailable(reservationDto);
        if (isBookAvailable) {
            Reservation reservation = reservationMapper.map(reservationDto, null);
            reservation.addReservationItem();
            reservationRepository.save(reservation);
        } else {
            throw new IllegalStateException("Book is not available");
        }
    }

    public boolean areBooksAvailable(ReservationDto reservationDto) {
        return reservationDto.getReservationItems()
                .stream()
                .allMatch(reservationItemDto -> {
                    List<ReservationItem> reservations = reservationItemRepository
                            .findByBookIdAndReturnedFalse(reservationItemDto.getBook().getId());
                    int numberOfReservedBooks = reservations.stream()
                            .map(ReservationItem::getQuantity)
                            .reduce(0, Integer::sum);
                    return reservationItemDto.getBook().getNumberOfBooks() - numberOfReservedBooks >= reservationItemDto.getQuantity();
                });
    }

}
