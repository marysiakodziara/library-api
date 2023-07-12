package com.example.libraryapi.repository;

import com.example.libraryapi.model.BookCategory;
import org.springframework.data.repository.CrudRepository;

public interface BookCategoryRepository extends CrudRepository<BookCategory, Integer> {
}
