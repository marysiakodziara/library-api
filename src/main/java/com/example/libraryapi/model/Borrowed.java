package com.example.libraryapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
public class Borrowed {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean returned;

    @ManyToMany
    @JoinTable(name="book_borrowed",
            joinColumns = @JoinColumn(name="borrowed_id"),
            inverseJoinColumns = @JoinColumn(name="book_id"))
    private List<Book> books = new ArrayList<>();

    @ManyToOne
    private User user;
}
