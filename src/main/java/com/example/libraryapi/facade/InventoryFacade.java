package com.example.libraryapi.facade;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.service.BookService;
import java.util.List;
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
}
