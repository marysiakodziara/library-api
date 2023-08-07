package com.example.libraryapi.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExtendedReservationItemDto {
    private Long id;
    private BookDto book;
    private Integer quantity;
    private boolean returned;
    private LocalDate reservationDate;
    private LocalDate endOfReservation;
    private String clientEmail;
}
