package com.example.libraryapi.service;

import com.example.libraryapi.dto.AvailableBooksCount;
import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.enums.GenreEnum;
import com.example.libraryapi.exceptions.ResourceAlreadyExistsException;
import com.example.libraryapi.mapper.BookMapper;
import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.ReservationItem;
import com.example.libraryapi.repository.BookRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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
        List<ReservationItem> reservationItems = pagedResult.getContent()
                .stream()
                .flatMap(book -> book.getReservationItems().stream()).toList();
        List<AvailableBooksCount> availableBooksCount = bookRepository.countNotReturnedBooks(reservationItems);
        return pagedResult.map(book -> BookMapper.INSTANCE.map(book, book.getNumberOfBooks() - Math.toIntExact(availableBooksCount.stream().filter(item -> Objects.equals(item.getBookId(), book.getId())).map(AvailableBooksCount::getCount).findFirst().orElse(0L))));
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "books_cache", allEntries = true),
                    @CacheEvict(value = "book_cache", allEntries = true),
                    @CacheEvict(value = "books_by_category_cache", allEntries = true),
                    @CacheEvict(value = "books_by_phrase_cache", allEntries = true),
                    @CacheEvict(value = "books_by_greater_id_cache", allEntries = true),
            }
    )
    public void addBook(BookDto bookDto) {

        if (bookRepository.findByIsbn(bookDto.getIsbn()).isPresent()) {
            throw new ResourceAlreadyExistsException("Book with isbn " + bookDto.getIsbn() + " already exists in database");
        }

        bookRepository.save(BookMapper.INSTANCE.map(bookDto));
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "books_cache", allEntries = true),
                    @CacheEvict(value = "book_cache", allEntries = true),
                    @CacheEvict(value = "books_by_category_cache", allEntries = true),
                    @CacheEvict(value = "books_by_phrase_cache", allEntries = true),
                    @CacheEvict(value = "books_by_greater_id_cache", allEntries = true),
            }
    )
    @Transactional
    public void updateBook(BookDto bookDto) {
        Book book = bookRepository.findById(bookDto.getId()).orElse(null);
        if (book == null)
            return;
        book.setNumberOfBooks(bookDto.getNumberOfBooks());
        book.setAuthor(bookDto.getAuthor());
    }

    @Cacheable(value = "book_cache")
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null)
            return null;
        int availableBooks = book.getNumberOfBooks() - (book.getReservationItems().stream().filter(item -> !item.isReturned()).map(ReservationItem::getQuantity).reduce(0, Integer::sum));
        return BookMapper.INSTANCE.map(book, availableBooks);
    }

    @Cacheable(value = "books_by_category_cache")
    public Page<BookDto> getBooksByCategory(List<GenreEnum> categories, int page, int size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Book> pagedResult = bookRepository.findByCategoriesIn(categories, paging);
        return mapWithNumberOfAvailableBooks(pagedResult);
    }

    @Cacheable(value = "books_by_phrase_cache")
    public Page<BookDto> getBooksContainingPhrase(String phrase, int page, int size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Book> pagedResult = bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(phrase, phrase, paging);
        return mapWithNumberOfAvailableBooks(pagedResult);
    }

    public Page<BookDto> getRandomBooks(int page, int size, String sortBy) {
        long count = bookRepository.count();
        return getByIdGreaterThanEqual((long) (Math.random() * count - 5), page, size, sortBy);
    }

    @Cacheable(value = "books_by_greater_id_cache", key = "#id")
    public Page<BookDto> getByIdGreaterThanEqual(Long id, int page, int size, String sortBy) {
        Page<Book> pagedResult = bookRepository.findByIdGreaterThanEqual(id, PageRequest.of(page, size, Sort.by(sortBy)));
        return mapWithNumberOfAvailableBooks(pagedResult);
    }

    public Page<BookDto> mapWithNumberOfAvailableBooks(Page<Book> pagedResult) {
        List<AvailableBooksCount> availableBooksCount = bookRepository.countNotReturnedBooks(pagedResult.getContent()
                .stream()
                .flatMap(book -> book.getReservationItems().stream()).toList());
        return pagedResult.map(book -> BookMapper.INSTANCE.map(book, book.getNumberOfBooks() - Math.toIntExact(availableBooksCount.stream().filter(item -> Objects.equals(item.getBookId(), book.getId())).map(AvailableBooksCount::getCount).findFirst().orElse(0L))));
    }

    @Cacheable(value = "book_by_isbn_cache")
    public BookDto getBookByIsbn(Long isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElse(null);
        if (book == null)
            return null;
        int availableBooks = book.getNumberOfBooks() - (book.getReservationItems().stream().filter(item -> !item.isReturned()).map(ReservationItem::getQuantity).reduce(0, Integer::sum));
        return BookMapper.INSTANCE.map(book, availableBooks);
    }

    public List<BookDto> getNewArrivals() {
        Sort sort = Sort.by("id").descending();
        Pageable paging = PageRequest.of(0, 12, sort);
        Page<Book> pagedResult = bookRepository.findAll(paging);
        List<ReservationItem> reservationItems = pagedResult.getContent()
                .stream()
                .flatMap(book -> book.getReservationItems().stream()).toList();
        List<AvailableBooksCount> availableBooksCount = bookRepository.countNotReturnedBooks(reservationItems);
        return (pagedResult.map(book -> BookMapper.INSTANCE.map(book, book.getNumberOfBooks() - Math.toIntExact(availableBooksCount.stream().filter(item -> Objects.equals(item.getBookId(), book.getId())).map(AvailableBooksCount::getCount).findFirst().orElse(0L))))).getContent();
    }
}
