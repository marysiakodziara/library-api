package com.example.libraryapi.controller;

import com.example.libraryapi.dto.ClientDto;
import com.example.libraryapi.facade.ClientFacade;
import com.example.libraryapi.security.ClientRole;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientFacade clientFacade;

    @PostMapping
    public void addUser(@RequestBody ClientDto clientDto) {
        clientFacade.addClient(clientDto);
    }

    @GetMapping
    public ClientDto getUser() throws IOException {
        return clientFacade.getClientDto();
    }

    @GetMapping("/byEmail")
    public ClientDto getUserByEmail(@RequestParam String email) {
        return clientFacade.getClientDtoByEmail(email);
    }

    @GetMapping("/role")
    public ClientRole getUserRole() {
        return clientFacade.getClientRole();
    }
}
