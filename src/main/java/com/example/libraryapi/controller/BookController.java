package com.example.libraryapi.controller;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.enums.GenreEnum;
import com.example.libraryapi.enums.MainGenreEnum;
import com.example.libraryapi.facade.BookFacade;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookFacade inventoryFacade;

    @PostMapping
    public void addBook(@RequestBody BookDto bookDto) {
        inventoryFacade.addBook(bookDto);
    }

    @PutMapping("/updateBook")
    public void updateBook(@RequestBody BookDto bookDto) {
        inventoryFacade.updateBook(bookDto);
    }

    @GetMapping
    public Page<BookDto> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "24") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return inventoryFacade.getAllBooks(page, size, sortBy);
    }

    @GetMapping("/categories")
    public Map<MainGenreEnum, List<GenreEnum>> getAllCategories() {
        return inventoryFacade.getAllCategories();
    }

    @GetMapping("/inCategory")
    public Page<BookDto> getBooksByCategory(
            @RequestParam List<GenreEnum> categories,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "24") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return inventoryFacade.getBooksByCategory(categories, page, size, sortBy);
    }

    @GetMapping("/filter")
    public Page<BookDto> getBooksContainingPhrase(
            @RequestParam String phrase,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "24") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        System.out.println("phrase = " + phrase);
        return inventoryFacade.getBooksContainingPhrase(phrase, page, size, sortBy);
    }

    @GetMapping("/id")
    public BookDto getBookById(@RequestParam Long id) {
        return inventoryFacade.getBookById(id);
    }

    @GetMapping("/random")
    public Page<BookDto> getBooksFromId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return inventoryFacade.getRandomBooks(page, size, sortBy);
    }

    @GetMapping("/isbn")
    public BookDto getBookByIsbn(@RequestParam Long isbn) {
        return inventoryFacade.getBookByIsbn(isbn);
    }

    @GetMapping("/newArrivals")
    public List<BookDto> getNewArrivals() {
        return inventoryFacade.getNewArrivals();
    }
}
