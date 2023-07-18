package com.example.libraryapi.service;

import com.example.libraryapi.dto.ClientDto;
import com.example.libraryapi.mapper.ClientMapper;
import com.example.libraryapi.model.Client;
import com.example.libraryapi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public void addUser(ClientDto clientDto) {
        clientRepository.save(clientMapper.map(clientDto));
    }

    public ClientDto getUser(String emailAddress) {
        return clientMapper.map(clientRepository.findByEmailAddress(emailAddress));
    }

    public void updateUser(ClientDto clientDto) {
        Client client = clientRepository.findByEmailAddress(clientDto.getEmailAddress());
        clientMapper.updateClient(clientDto, client);
        clientRepository.save(client);
    }
}
