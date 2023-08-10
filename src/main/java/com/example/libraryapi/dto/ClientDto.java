package com.example.libraryapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
}
