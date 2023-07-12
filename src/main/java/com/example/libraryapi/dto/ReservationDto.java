package com.example.libraryapi.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private Long id;
    private List<BookDto> books;
    private UserDto user;
    private LocalDate endOfReservation;
    private boolean borrowed;
    private boolean canceled;
}
