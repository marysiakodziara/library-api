package com.example.libraryapi.controller;

import com.example.libraryapi.dto.ReservationDto;
import com.example.libraryapi.facade.ReservationFacade;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    private final ReservationFacade reservationFacade;

    @PostMapping
    public ResponseEntity<String> addReservation(@RequestBody ReservationDto reservationDto) {
        return reservationFacade.addReservation(reservationDto);
    }

    @GetMapping
    public List<ReservationDto> getAllReservations() {
        return reservationFacade.getAllReservations();
    }

    @GetMapping("/client")
    public Page<ReservationDto> getReservationByUserEmail(
            @RequestParam String emailAddress,
            @RequestParam boolean borrowed,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return reservationFacade.getReservationsByUserEmail(emailAddress, borrowed, page, size, sortBy);
    }
}
