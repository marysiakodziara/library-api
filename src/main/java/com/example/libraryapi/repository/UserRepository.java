package com.example.libraryapi.repository;

import com.example.libraryapi.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Client, Long> {
}
