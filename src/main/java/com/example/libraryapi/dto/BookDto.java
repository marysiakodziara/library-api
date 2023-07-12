package com.example.libraryapi.dto;

import com.example.libraryapi.enums.GenreEnum;
import com.example.libraryapi.model.Book;
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

    private Long isbn;
    private String title;
    private String author;
    private Set<BookCategoryDto> categories;
}
