package com.example.libraryapi.repository;

import com.example.libraryapi.model.Client;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, UUID> {
    Client findByEmailAddress(String emailAddress);
}
