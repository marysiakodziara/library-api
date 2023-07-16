package com.example.libraryapi.facade;

import com.example.libraryapi.dto.ReservationDto;
import com.example.libraryapi.service.ReservationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationFacade {

    private final ReservationService reservationService;

    public void addReservation(ReservationDto reservationDto) {
        reservationService.addReservation(reservationDto);
    }

    public List<ReservationDto> getAllReservations() {
        return reservationService.getAllReservations();
    }
}

