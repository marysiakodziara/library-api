package com.example.libraryapi.service;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.enums.GenreEnum;
import com.example.libraryapi.mapper.BookMapper;
import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.Reservation;
import com.example.libraryapi.repository.BookRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookDto> getAllBooks(int page, int size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Book> pagedResult = bookRepository.findAll(paging);
        return pagedResult.stream()
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

    public List<BookDto> getBooksByCategory(List<GenreEnum> categories, int page, int size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Book> pagedResult = bookRepository.findByCategoriesIn(categories, paging);
        return pagedResult.stream()
                .map(BookMapper.INSTANCE::map)
                .collect(Collectors.toList());
    }

    public List<BookDto> getBooksContainingPhrase(String phrase, int page, int size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Book> pagedResult = bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(phrase, phrase, paging);
        return pagedResult.stream()
                .map(BookMapper.INSTANCE::map)
                .collect(Collectors.toList());
    }
}
