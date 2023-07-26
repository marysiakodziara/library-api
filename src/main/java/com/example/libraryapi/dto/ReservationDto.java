package com.example.libraryapi.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private UUID id;
    private List<ReservationItemDto> reservationItems;
    private LocalDate reservationDate;
    private LocalDate endOfReservation;
    private boolean borrowed;
}
