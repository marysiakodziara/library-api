package com.example.libraryapi.service;

import com.example.libraryapi.dto.ExtendedReservationDto;
import com.example.libraryapi.dto.ExtendedReservationItemDto;
import com.example.libraryapi.dto.ReservationDto;
import com.example.libraryapi.mapper.ReservationItemMapper;
import com.example.libraryapi.mapper.ReservationMapper;
import com.example.libraryapi.model.Client;
import com.example.libraryapi.model.Reservation;
import com.example.libraryapi.model.ReservationItem;
import com.example.libraryapi.repository.ReservationItemRepository;
import com.example.libraryapi.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationItemRepository reservationItemRepository;
    private final ReservationMapper reservationMapper;
    private final ReservationItemMapper reservationItemMapper;


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
                    @CacheEvict(value = "reservation_items_cache", allEntries = true),
                    @CacheEvict(value = "reservation_item_by_client_cache", allEntries = true),
            }
    )
    @Transactional
    public void addReservation(ReservationDto reservationDto, Client client) {
        boolean isBookAvailable = this.areBooksAvailable(reservationDto);
        if (isBookAvailable) {
            Reservation reservation = reservationMapper.map(reservationDto, client);
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

    public Page<ReservationDto> getReservationsByUserEmail(String emailAddress, boolean borrowed, int page, int size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        return reservationRepository.findByClient_EmailAddressAndBorrowed(emailAddress, borrowed, paging)
                .map(reservationMapper::map);

    }

    @Caching(
            evict = {
                    @CacheEvict(value = "books_cache", allEntries = true),
                    @CacheEvict(value = "book_cache", allEntries = true),
                    @CacheEvict(value = "books_by_category_cache", allEntries = true),
                    @CacheEvict(value = "books_by_phrase_cache", allEntries = true),
                    @CacheEvict(value = "reservation_items_cache", allEntries = true),
                    @CacheEvict(value = "reservation_item_by_client_cache", allEntries = true),
            }
    )
    @Transactional
    public void cancelReservation(UUID reservationId, Client client) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null && reservation.getClient().getId().equals(client.getId())) {
            reservation.setCanceled(true);
            reservationRepository.save(reservation);
            (reservation.getReservationItems()).forEach(reservationItem -> {
                reservationItem.setReturned(true);
                reservationItemRepository.save(reservationItem);
            });
        }
    }

    @Cacheable(value = "reservation_items_cache")
    public Page<ExtendedReservationItemDto> getReservationItems(int page) {
        Pageable paging = PageRequest.of(page, 500, Sort.by("reservation.reservationDate").descending());
        Page<ReservationItem> reservationItems = reservationItemRepository.findByReservation_BorrowedTrueAndReturnedFalse(paging);
        return this.mapToDto(reservationItems);
    }

    @Cacheable(value = "reservation_items_by_client_cache", key = "#emailAddress")
    public Page<ExtendedReservationItemDto> getReservationItemsByClient(String emailAddress, int page) {
        Pageable paging = PageRequest.of(page, 500, Sort.by("reservation.reservationDate").descending());
        Page<ReservationItem> reservationItems = reservationItemRepository.findByReservation_Client_EmailAddressAndReservation_BorrowedTrueAndReturnedFalse(emailAddress, paging);
        return this.mapToDto(reservationItems);
    }

    public Page<ExtendedReservationItemDto> getOverdueReservationItems(int page) {
        Pageable paging = PageRequest.of(page, 500, Sort.by("reservation.reservationDate").descending());
        LocalDate now = LocalDate.now();
        Page<ReservationItem> reservationItems = reservationItemRepository.findByReservation_BorrowedTrueAndReturnedFalseAndReservation_EndOfReservationBefore(now, paging);
        return this.mapToDto(reservationItems);
    }

    public Page<ExtendedReservationDto> getReservations(int page) {
        Pageable paging = PageRequest.of(page, 500, Sort.by("reservationDate").descending());
        Page<Reservation> reservations = reservationRepository.findByCanceledFalseAndBorrowedFalse(paging);
        return reservations.map(reservation -> reservationMapper.map(reservation, reservation.getClient().getEmailAddress()));
    }

    public Page<ExtendedReservationItemDto> mapToDto(Page<ReservationItem> reservationItems) {
        List<Reservation> reservationList = reservationRepository.findByReservationItemsIn(reservationItems.toList());
        return reservationItems.map(reservationItem -> {
            Reservation reservation = reservationList.stream().filter(reservation1 -> reservation1.getReservationItems().contains(reservationItem)).findFirst().orElse(null);
            return reservationItemMapper.map(reservationItem, reservation.getClient().getEmailAddress(), reservation.getReservationDate(), reservation.getEndOfReservation());
        });
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "books_cache", allEntries = true),
                    @CacheEvict(value = "book_cache", allEntries = true),
                    @CacheEvict(value = "books_by_category_cache", allEntries = true),
                    @CacheEvict(value = "books_by_phrase_cache", allEntries = true),
                    @CacheEvict(value = "reservation_items_cache", allEntries = true),
                    @CacheEvict(value = "reservation_item_by_client_cache", allEntries = true),
            }
    )
    public void returnBooks(Long reservationItemId) {
        if (reservationItemRepository.findById(reservationItemId).isPresent()) {
            ReservationItem reservationItem = reservationItemRepository.findById(reservationItemId).get();
            reservationItem.setReturned(true);
            reservationItemRepository.save(reservationItem);
        }
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "reservation_items_cache", allEntries = true),
                    @CacheEvict(value = "reservation_item_by_client_cache", allEntries = true),
            }
    )
    @Transactional
    public void confirmReservation(UUID id) {
        if (reservationRepository.findById(id).isPresent()) {
            Reservation reservationForBorrow = new Reservation();
            Reservation reservation = reservationRepository.findById(id).get();
            List<ReservationItem> reservationItems = reservation.getReservationItems().stream().map(reservationItem -> {
                ReservationItem reservationItem1 = new ReservationItem();
                reservationItem1.setBook(reservationItem.getBook());
                reservationItem1.setQuantity(reservationItem.getQuantity());
                return reservationItem1;
            }).toList();
            reservationForBorrow.setClient(reservation.getClient());
            reservationForBorrow.setReservationItems(reservationItems);
            reservationForBorrow.addReservationItem();
            reservationForBorrow.setBorrowed(true);
            reservationForBorrow.setReservationDate(LocalDate.now());
            reservationForBorrow.setEndOfReservation(LocalDate.now().plusDays(7));
            reservationRepository.save(reservationForBorrow);
            reservation.setCanceled(true);
            reservationRepository.save(reservation);
            reservation.getReservationItems().forEach(reservationItem -> {
                reservationItem.setReturned(true);
                reservationItemRepository.save(reservationItem);
            });
        }
    }
}
