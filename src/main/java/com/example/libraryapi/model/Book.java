package com.example.libraryapi.model;

import com.example.libraryapi.enums.GenreEnum;
import jakarta.persistence.*;
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
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private Long isbn;
    private String author;
    private int numberOfBooks;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<GenreEnum> categories;

    @ManyToMany
    @JoinTable(name="book_reservation",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="reservation_id"))
    private List<Reservation> reservations;

    @ManyToMany(mappedBy = "books")
    private List<Borrowed> borrowedList;
}
