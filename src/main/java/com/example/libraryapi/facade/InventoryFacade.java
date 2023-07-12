package com.example.libraryapi.facade;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.dto.PostBookDto;
import com.example.libraryapi.service.BookService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryFacade {

    private final BookService bookService;

    public void addBook(PostBookDto postBookDto) {
        bookService.addBook(postBookDto);
    }

    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }
}
