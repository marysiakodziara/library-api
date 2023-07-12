package com.example.libraryapi.dto;

import com.example.libraryapi.enums.GenreEnum;
import com.example.libraryapi.enums.MainGenreEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCategoryDto {

    private Integer id;
    private GenreEnum literaryGenre;
    private MainGenreEnum mainGenre;
}
