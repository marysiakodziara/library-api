package com.example.libraryapi.facade;

import com.example.libraryapi.dto.ReservationDto;
import com.example.libraryapi.security.ClientResolver;
import com.example.libraryapi.service.ReservationService;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationFacade {

    private final ReservationService reservationService;
    private final ClientFacade clientFacade;

    public ResponseEntity<String> addReservation(ReservationDto reservationDto) {
        try {
            reservationService.addReservation(reservationDto, clientFacade.getUser());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalStateException | IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public List<ReservationDto> getAllReservations() {
        return reservationService.getAllRecordsFromReservation();
    }

    public Page<ReservationDto> getReservationsByUserEmail(String emailAddress, boolean borrowed, int page, int size, String sortBy) {
        return reservationService.getReservationsByUserEmail(emailAddress, borrowed, page, size, sortBy);
    }

    public Page<ReservationDto> getUserReservations(boolean borrowed, int page, int size, String sortBy) throws IOException {
        return reservationService.getReservationsByUserEmail(ClientResolver.loggedUserEmailResolver(), borrowed, page, size, sortBy);
    }

    public void cancelReservation(UUID reservationId) throws IOException {
        reservationService.cancelReservation(reservationId, clientFacade.getUser());
    }
}

