package com.example.libraryapi.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private LocalDate reservationDate;
    private LocalDate endOfReservation;
    private boolean borrowed;
    private boolean returned;
    private boolean canceled;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<ReservationItem> reservationItems = new ArrayList<>();

    public void addReservationItem() {
        this.reservationItems.forEach(reservationItem -> reservationItem.setReservation(this));
    }
}
