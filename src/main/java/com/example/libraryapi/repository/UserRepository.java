package com.example.libraryapi.repository;

import com.example.libraryapi.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
