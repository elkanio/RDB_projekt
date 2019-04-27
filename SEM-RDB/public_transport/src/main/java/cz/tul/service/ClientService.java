package cz.tul.service;

import cz.tul.data.Client;
import cz.tul.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void create(Client client) {
        clientRepository.save(client);
    }

    public boolean exists(String username) {
        return clientRepository.exists(username);
    }

    public List<Client> getAllClients() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void deleteClients() {
        clientRepository.deleteAll();
    }
}
