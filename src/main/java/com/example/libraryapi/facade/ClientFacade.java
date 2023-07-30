package com.example.libraryapi.facade;

import com.example.libraryapi.dto.ClientDto;
import com.example.libraryapi.security.ClientResolver;
import com.example.libraryapi.service.ClientService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientFacade {

    private final ClientService clientService;

    public void addUser(ClientDto clientDto) {
        clientService.addUser(clientDto);
    }

    public ClientDto getUser() throws IOException {
        return clientService.getUser(ClientResolver.loggedUserEmailResolver());
    }

    public void updateUser(ClientDto clientDto) {
        clientService.updateUser(clientDto);
    }
}
