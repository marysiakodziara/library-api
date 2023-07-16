package com.example.libraryapi.controller;

import com.example.libraryapi.dto.ReservationDto;
import com.example.libraryapi.facade.ReservationFacade;
import com.example.libraryapi.model.Book;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    private final ReservationFacade reservationFacade;

    @PostMapping
    public void addReservation(@RequestBody ReservationDto reservationDto) {
        reservationFacade.addReservation(reservationDto);
    }

    @GetMapping
    public List<ReservationDto> getAllReservations() {
        return reservationFacade.getAllReservations();
    }

}
