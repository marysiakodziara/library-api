package com.example.libraryapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private LocalDate endOfReservation;
    private boolean borrowed;
    private boolean canceled;

    @ManyToOne
    private User userId;

    @ManyToMany(mappedBy = "reservations")
    private List<Book> books = new ArrayList<>();
}
