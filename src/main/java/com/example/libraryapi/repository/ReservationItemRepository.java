package com.example.libraryapi.repository;

import com.example.libraryapi.dto.ExtendedReservationItemDto;
import com.example.libraryapi.mapper.ReservationMapper;
import com.example.libraryapi.model.ReservationItem;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReservationItemRepository extends CrudRepository<ReservationItem, Long> {
    List<ReservationItem> findByBookIdAndReturnedFalse(Long id);

    Page<ReservationItem> findByReservation_BorrowedTrueAndReturnedFalseAndReservation_EndOfReservationBefore(LocalDate date, Pageable paging);

    Page<ReservationItem> findByReservation_BorrowedTrueAndReturnedFalse(Pageable paging);

    Page<ReservationItem> findByReservation_Client_EmailAddressAndReservation_BorrowedTrueAndReturnedFalse(String emailAddress, Pageable paging);
}
