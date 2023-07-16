package com.example.libraryapi.repository;

import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.Reservation;
import com.example.libraryapi.model.ReservationItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

}
