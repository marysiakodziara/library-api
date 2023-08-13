package com.example.libraryapi.model;

import com.example.libraryapi.enums.GenreEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private Long isbn;
    @NotNull
    private String author;
    @NotNull
    private int numberOfBooks;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<GenreEnum> categories;

    @OneToMany(mappedBy = "book")
    private List<ReservationItem> reservationItems = new ArrayList<>();
}
