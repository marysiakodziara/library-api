package com.example.libraryapi.service;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.dto.PostBookDto;
import com.example.libraryapi.mapper.BookMapper;
import com.example.libraryapi.model.Book;
import com.example.libraryapi.repository.BookRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookDto> getAllBooks() {

        Iterable<Book> books = bookRepository.findAll();
        return StreamSupport.stream(books.spliterator(), false)
                .map(bookMapper::map)
                .collect(Collectors.toList());
    }

    public void addBook(PostBookDto postBookDto) {

        if (bookRepository.findByIsbn(postBookDto.getIsbn()).isPresent()) {
            throw new RuntimeException("Book with isbn " + postBookDto.getIsbn() + " already exists");
        }

        bookRepository.save(bookMapper.map(postBookDto));
    }
}
