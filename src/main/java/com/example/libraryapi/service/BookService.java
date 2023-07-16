package com.example.libraryapi.service;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.mapper.BookMapper;
import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.Reservation;
import com.example.libraryapi.repository.BookRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookDto> getAllBooks() {

        Iterable<Book> books = bookRepository.findAll();
        return StreamSupport.stream(books.spliterator(), false)
                .map(BookMapper.INSTANCE::map)
                .collect(Collectors.toList());
    }

    public void addBook(BookDto bookDto) {

        if (bookRepository.findByIsbn(bookDto.getIsbn()).isPresent()) {
            throw new RuntimeException("Book with isbn " + bookDto.getIsbn() + " already exists");
        }

        bookRepository.save(BookMapper.INSTANCE.map(bookDto));
    }

    public Book findByIsbn(Long isbn) {
        return bookRepository.findByIsbn(isbn).orElse(null);
    }


}
