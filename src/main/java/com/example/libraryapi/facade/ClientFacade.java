package com.example.libraryapi.facade;

import com.example.libraryapi.dto.ClientDto;
import com.example.libraryapi.model.Client;
import com.example.libraryapi.security.ClientResolver;
import com.example.libraryapi.security.ClientRole;
import com.example.libraryapi.service.ClientService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientFacade {

    private final ClientService clientService;

    public void addClient(ClientDto clientDto) {
        clientService.addUser(clientDto);
    }

    public ClientDto getClientDto() throws IOException {
        return clientService.getUserDto(ClientResolver.loggedUserEmailResolver());
    }

    public Client getClient() throws IOException {
        return clientService.getUser(ClientResolver.loggedUserEmailResolver());
    }

    public void updateClient(ClientDto clientDto) {
        clientService.updateUser(clientDto);
    }

     public ClientRole getClientRole() {
        return ClientResolver.resolveClientRole();
    }

    public ClientDto getClientDtoByEmail(String email) {
        if (ClientRole.MANAGER.equals(ClientResolver.resolveClientRole())) {
            return clientService.getUserDto(email);
        }
        return null;
    }

    public Client getClientByEmail(String email) {
        if (ClientRole.MANAGER.equals(ClientResolver.resolveClientRole())) {
            return clientService.getUser(email);
        }
        return null;
    }
}
