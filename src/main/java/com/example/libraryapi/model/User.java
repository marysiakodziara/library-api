package com.example.libraryapi.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
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
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private String firstName;
    private String lastName;
    private String emailAddress;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Borrowed> borrowedBooks = new HashSet<>();
}
