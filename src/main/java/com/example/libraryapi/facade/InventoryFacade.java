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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryFacade {

    private final BookService bookService;

    public void addBook(BookDto bookDto) {
        bookService.addBook(bookDto);
    }

    public void updateBook(BookDto bookDto) {
        bookService.updateBook(bookDto);
    }

    public Page<BookDto> getAllBooks(int page, int size, String sortBy) {
        return bookService.getAllBooks(page, size, sortBy);
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

    public Page<BookDto> getBooksByCategory(List<GenreEnum> categories, int page, int size, String sortBy) {
        return bookService.getBooksByCategory(categories, page, size, sortBy);
    }

    public Page<BookDto> getBooksContainingPhrase(String phrase, int page, int size, String sortBy) {
        return bookService.getBooksContainingPhrase(phrase, page, size, sortBy);
    }

    public BookDto getBookById(Long id) {
        return bookService.getBookById(id);
    }

    public Page<BookDto> getRandomBooks(int page, int size, String sortBy) {
        return bookService.getRandomBooks(page, size, sortBy);
    }

    public BookDto getBookByIsbn(Long isbn) {
        return bookService.getBookByIsbn(isbn);
    }
}
