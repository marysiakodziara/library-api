package com.example.libraryapi.repository;

import com.example.libraryapi.dto.AvailableBooksCount;
import com.example.libraryapi.enums.GenreEnum;
import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.ReservationItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(Long isbn);

    Page<Book> findByCategoriesIn(@Param("categories") List<GenreEnum> categories, Pageable paging);

    Page<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String titlePhrase, String authorPhrase, Pageable paging);

    Page<Book> findByIdGreaterThanEqual(Long id, Pageable paging);

    @Query("""
        SELECT new com.example.libraryapi.dto.AvailableBooksCount(ri.book.id, SUM(ri.quantity))
        FROM ReservationItem ri
        WHERE ri IN :reservationItems
        AND ri.returned = false
        GROUP BY ri.book.id
        """)
    List<AvailableBooksCount> countNotReturnedBooks(@Param("reservationItems") List<ReservationItem> reservationItems);
}
