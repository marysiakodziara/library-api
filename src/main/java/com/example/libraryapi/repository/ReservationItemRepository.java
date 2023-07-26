package com.example.libraryapi.repository;

import com.example.libraryapi.model.ReservationItem;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ReservationItemRepository extends CrudRepository<ReservationItem, Long> {
    List<ReservationItem> findByBookIdAndReturnedFalse(Long id);
}
