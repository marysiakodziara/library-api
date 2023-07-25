package com.example.libraryapi.service;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.enums.GenreEnum;
import com.example.libraryapi.mapper.BookMapper;
import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.ReservationItem;
import com.example.libraryapi.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Cacheable(value = "books_cache")
    public Page<BookDto> getAllBooks(int page, int size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Book> pagedResult = bookRepository.findAll(paging);
        return pagedResult.map(book -> BookMapper.INSTANCE.map(book, getNumberOfAvailableBooks(book)));
    }

    public void addBook(BookDto bookDto) {

        if (bookRepository.findByIsbn(bookDto.getIsbn()).isPresent()) {
            throw new RuntimeException("Book with isbn " + bookDto.getIsbn() + " already exists");
        }

        bookRepository.save(BookMapper.INSTANCE.map(bookDto));
    }

    @Cacheable(value = "book_cache")
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        return BookMapper.INSTANCE.map(bookRepository.findById(id).orElse(null), getNumberOfAvailableBooks(book));
    }

    public int getNumberOfAvailableBooks(Book book) {
        if (book == null)
            return 0;
        return book.getNumberOfBooks() - (book.getReservationItems().stream().filter(item -> !item.getReservation().isReturned()).map(ReservationItem::getQuantity).toList()).size();
    }

    @Cacheable(value = "books_by_category_cache")
    public Page<BookDto> getBooksByCategory(List<GenreEnum> categories, int page, int size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Book> pagedResult = bookRepository.findByCategoriesIn(categories, paging);
        return pagedResult.map(book -> BookMapper.INSTANCE.map(book, getNumberOfAvailableBooks(book)));
    }

    @Cacheable(value = "books_by_phrase_cache")
    public Page<BookDto> getBooksContainingPhrase(String phrase, int page, int size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Book> pagedResult = bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(phrase, phrase, paging);
        return pagedResult.map(book -> BookMapper.INSTANCE.map(book, getNumberOfAvailableBooks(book)));
    }

    public Page<BookDto> getRandomBooks(int page, int size, String sortBy) {
        long count = bookRepository.count();
        return getByIdGreaterThanEqual((long) (Math.random() * count - 5), page, size, sortBy);
    }

    @Cacheable(value = "books_by_greater_id_cache", key = "#id")
    public Page<BookDto> getByIdGreaterThanEqual(Long id, int page, int size, String sortBy) {
        Page<Book> pagesResult = bookRepository.findByIdGreaterThanEqual(id, PageRequest.of(page, size, Sort.by(sortBy)));
        return pagesResult.map(book -> BookMapper.INSTANCE.map(book, getNumberOfAvailableBooks(book)));
    }
}
