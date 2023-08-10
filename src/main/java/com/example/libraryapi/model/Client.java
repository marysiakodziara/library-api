package com.example.libraryapi.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.Set;
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
public class Client {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Email
    @NotNull
    private String emailAddress;
    @NotNull
    private String phoneNumber;
    @OneToMany(mappedBy = "client")
    private Set<Reservation> reservations;
}
