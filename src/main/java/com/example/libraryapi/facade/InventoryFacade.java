package com.example.libraryapi.facade;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.enums.GenreEnum;
import com.example.libraryapi.enums.MainGenreEnum;
import com.example.libraryapi.service.BookService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryFacade {

    private final BookService bookService;

    public void addBook(BookDto bookDto) {
        bookService.addBook(bookDto);
    }

    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    public Map<MainGenreEnum, List<GenreEnum>> getAllCategories() {
        Map<MainGenreEnum, List<GenreEnum>> genresByMainGenre = new HashMap<>();
        for (GenreEnum genre : GenreEnum.values()) {
            MainGenreEnum mainGenre = genre.getMainGenreEnum();
            if (!genresByMainGenre.containsKey(mainGenre)) {
                genresByMainGenre.put(mainGenre, new ArrayList<>());
            }
            genresByMainGenre.get(mainGenre).add(genre);
        }
        return genresByMainGenre;
    }
}
