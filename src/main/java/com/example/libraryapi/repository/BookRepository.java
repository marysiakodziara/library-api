package com.example.libraryapi.repository;

import com.example.libraryapi.model.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Object> findByIsbn(Long isbn);
}
