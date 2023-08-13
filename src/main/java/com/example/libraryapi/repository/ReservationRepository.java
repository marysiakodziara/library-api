package com.example.libraryapi.repository;

import com.example.libraryapi.mapper.ReservationItemMapper;
import com.example.libraryapi.model.Reservation;
import com.example.libraryapi.model.ReservationItem;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    List<Reservation> findByReservationItemsIn(List<ReservationItem> toList);

    Page<Reservation> findByCanceledFalseAndBorrowedFalse(Pageable paging);

    @Query("""
                SELECT r
                FROM Reservation r
                LEFT JOIN FETCH r.reservationItems
                WHERE r.canceled = false
                AND r.borrowed = false
                AND r.endOfReservation < ?1""")
    List<Reservation> findExpiredReservations(LocalDate today);

    Page<Reservation> findByClient_EmailAddressAndBorrowedAndCanceledFalse(String emailAddress, boolean borrowed, Pageable paging);
}
