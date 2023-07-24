package com.example.libraryapi.service;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.enums.GenreEnum;
import com.example.libraryapi.mapper.BookMapper;
import com.example.libraryapi.model.Book;
import com.example.libraryapi.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Page<BookDto> getAllBooks(int page, int size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Book> pagedResult = bookRepository.findAll(paging);
        return pagedResult.map(BookMapper.INSTANCE::map);
    }

    public void addBook(BookDto bookDto) {

        if (bookRepository.findByIsbn(bookDto.getIsbn()).isPresent()) {
            throw new RuntimeException("Book with isbn " + bookDto.getIsbn() + " already exists");
        }

        bookRepository.save(BookMapper.INSTANCE.map(bookDto));
    }

    public BookDto getBookById(Long id) {
        return BookMapper.INSTANCE.map(bookRepository.findById(id).orElse(null));
    }

    public Page<BookDto> getBooksByCategory(List<GenreEnum> categories, int page, int size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Book> pagedResult = bookRepository.findByCategoriesIn(categories, paging);
        return pagedResult.map(BookMapper.INSTANCE::map);
    }

    public Page<BookDto> getBooksContainingPhrase(String phrase, int page, int size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Book> pagedResult = bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(phrase, phrase, paging);
        return pagedResult.map(BookMapper.INSTANCE::map);
    }

    public Page<BookDto> getRandomBooks(int page, int size, String sortBy) {
        long count = bookRepository.count();
        Page<Book> pagesResult = bookRepository.findByIdGreaterThanEqual((long) (Math.random() * count - 4), PageRequest.of(page, size, Sort.by(sortBy)));
        return pagesResult.map(BookMapper.INSTANCE::map);
    }
}
