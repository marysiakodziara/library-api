package com.example.libraryapi.repository;

import com.example.libraryapi.model.Reservation;
import com.example.libraryapi.model.ReservationItem;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    Page<Reservation> findByClient_EmailAddressAndBorrowed(String emailAddress, boolean borrowed, Pageable paging);

    List<Reservation> findByReservationItemsIn(List<ReservationItem> toList);

    Page<Reservation> findByCanceledFalseAndBorrowedFalse(Pageable paging);
}
