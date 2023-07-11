package com.example.libraryapi.service;

import com.example.libraryapi.repository.BorrowedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowedService {
    private final BorrowedRepository borrowedRepository;
}
