package com.example.libraryapi.dto;

import com.example.libraryapi.enums.GenreEnum;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    private Long isbn;
    private String title;
    private String author;
    private Set<GenreEnum> categories;
    private int numberOfBooks;
    private int numberOfAvailableBooks;
}
