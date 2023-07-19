package com.example.libraryapi.controller;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.enums.GenreEnum;
import com.example.libraryapi.enums.MainGenreEnum;
import com.example.libraryapi.facade.InventoryFacade;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {

    private final InventoryFacade inventoryFacade;

    @PostMapping
    public void addBook(@RequestBody BookDto bookDto) {
        inventoryFacade.addBook(bookDto);
    }

    @GetMapping
    public List<BookDto> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return inventoryFacade.getAllBooks(page, size, sortBy);
    }

    @GetMapping("/categories")
    public Map<MainGenreEnum, List<GenreEnum>> getAllCategories() {
        return inventoryFacade.getAllCategories();
    }

    @GetMapping("/inCategory")
    public List<BookDto> getBooksByCategory(
            @RequestParam List<GenreEnum> categories,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return inventoryFacade.getBooksByCategory(categories, page, size, sortBy);
    }

    @GetMapping("/filter")
    public List<BookDto> getBooksContainingPhrase(
            @RequestParam String phrase,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return inventoryFacade.getBooksContainingPhrase(phrase, page, size, sortBy);
    }
}
