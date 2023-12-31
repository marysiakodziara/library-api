package com.example.libraryapi.service;

import com.example.libraryapi.model.Reservation;
import com.example.libraryapi.repository.ReservationItemRepository;
import com.example.libraryapi.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduledTaskService {

    private final ReservationRepository reservationRepository;
    private final ReservationItemRepository reservationItemRepository;

    @Scheduled(cron = "${com.scheduled.cron}")
    public void deleteExpiredReservations() {
        LocalDate today = LocalDate.now();
        List<Reservation> expiredReservations = reservationRepository.findExpiredReservations(today);
        expiredReservations.forEach(this::deleteReservation);
    }
    @Transactional
    public void deleteReservation(Reservation reservation) {
        reservation.setCanceled(true);
        reservationRepository.save(reservation);
        (reservation.getReservationItems()).forEach(reservationItem -> {
            reservationItem.setReturned(true);
            reservationItemRepository.save(reservationItem);
        });
    }
}
