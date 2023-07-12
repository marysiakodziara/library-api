package com.example.libraryapi.controller;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.dto.PostBookDto;
import com.example.libraryapi.facade.InventoryFacade;
import com.example.libraryapi.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {

    private final InventoryFacade inventoryFacade;

    @PostMapping
    public void addBook(@RequestBody PostBookDto postBookDto) {
        inventoryFacade.addBook(postBookDto);
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return inventoryFacade.getAllBooks();
    }
}
