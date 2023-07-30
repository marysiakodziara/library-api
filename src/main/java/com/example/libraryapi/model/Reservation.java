package com.example.libraryapi.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<ReservationItem> reservationItems = new ArrayList<>();

    public void addReservationItem() {
        this.reservationItems.forEach(reservationItem -> reservationItem.setReservation(this));
    }
}
