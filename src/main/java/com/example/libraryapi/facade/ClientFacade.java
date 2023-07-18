package com.example.libraryapi.facade;

import com.example.libraryapi.dto.ClientDto;
import com.example.libraryapi.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientFacade {

    private final ClientService clientService;

    public void addUser(ClientDto clientDto) {
        clientService.addUser(clientDto);
    }

    public ClientDto getUser(String emailAddress) {
        return clientService.getUser(emailAddress);
    }

    public void updateUser(ClientDto clientDto) {
        clientService.updateUser(clientDto);
    }
}
