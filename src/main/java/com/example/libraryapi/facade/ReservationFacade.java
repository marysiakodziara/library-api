package com.example.libraryapi.facade;

import com.example.libraryapi.dto.ReservationDto;
import com.example.libraryapi.service.ReservationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationFacade {

    private final ReservationService reservationService;

    public ResponseEntity<String> addReservation(ReservationDto reservationDto) {
        try {
            reservationService.addReservation(reservationDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public List<ReservationDto> getAllReservations() {
        return reservationService.getAllRecordsFromReservation();
    }

    public Page<ReservationDto> getReservationsByUserEmail(String emailAddress, boolean borrowed, int page, int size, String sortBy) {
        return reservationService.getReservationsByUserEmail(emailAddress, borrowed, page, size, sortBy);
    }
}

