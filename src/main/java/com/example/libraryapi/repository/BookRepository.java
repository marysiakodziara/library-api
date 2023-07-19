package com.example.libraryapi.repository;

import com.example.libraryapi.enums.GenreEnum;
import com.example.libraryapi.model.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByIsbn(Long isbn);

    Page<Book> findByCategoriesIn(@Param("categories")List<GenreEnum> categories, Pageable paging);

    Page<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String titlePhrase, String authorPhrase, Pageable paging);
}
