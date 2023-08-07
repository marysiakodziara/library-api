package com.example.libraryapi.facade;

import com.example.libraryapi.dto.ClientDto;
import com.example.libraryapi.dto.ExtendedReservationDto;
import com.example.libraryapi.dto.ExtendedReservationItemDto;
import com.example.libraryapi.dto.ReservationDto;
import com.example.libraryapi.exceptions.NotQualifiedException;
import com.example.libraryapi.exceptions.ResourceAlreadyExistsException;
import com.example.libraryapi.security.ClientResolver;
import com.example.libraryapi.security.ClientRole;
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
            reservationService.addReservation(reservationDto, clientFacade.getClient());
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
        reservationService.cancelReservation(reservationId, clientFacade.getClient());
    }

    public void borrowBooks(ClientDto clientDto, ReservationDto reservationDto) {
        if (ClientRole.MANAGER.equals(clientFacade.getClientRole())) {
            reservationService.addReservation(reservationDto, clientFacade.getClientByEmail(clientDto.getEmailAddress()));
        } else {
            throw new NotQualifiedException("You are not authorized to borrow books");
        }
    }

    public Page<ExtendedReservationItemDto> getReservationItems(int page) {
        return reservationService.getReservationItems(page);
    }

    public Page<ExtendedReservationItemDto> getReservationItemsByClient(int page, String clientEmail) {
        return reservationService.getReservationItemsByClient(clientEmail,page);
    }

    public Page<ExtendedReservationDto> getReservations(int page) {
        return reservationService.getReservations(page);
    }

    public Page<ExtendedReservationItemDto> getOverdueReservationItems(int page) {
        return reservationService.getOverdueReservationItems(page);
    }

    public void returnBooks(Long reservationItemId) {
        reservationService.returnBooks(reservationItemId);
    }

    public void confirmReservation(UUID reservationId) {
        reservationService.confirmReservation(reservationId);
    }
}

