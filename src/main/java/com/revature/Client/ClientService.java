package com.revature.Client;

import com.revature.util.Serviceable;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientService implements Serviceable<Client> {
    private final ClientRepository clientRepository;


    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public List<Client> findAll() {
        List<Client> clientList;
        try {
            clientRepository.establishConnection();
            clientList= clientRepository.findAll();
        } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                clientRepository.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return clientList;

    }

    @Override
    public Optional<Client> findById(int id) {
        Optional<Client> foundClient;
        try {
            clientRepository.establishConnection();
            foundClient= clientRepository.findById(id);
        } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                clientRepository.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return foundClient;
    }

    public Client create(Client client) {
        try {
            clientRepository.establishConnection();
            clientRepository.save(client);
        } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                clientRepository.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return client;

    }

    public void delete(int id) {
        try {
            clientRepository.establishConnection();
            clientRepository.delete(id);
        } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                clientRepository.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void update(Client toUpdate) {
        try {
            clientRepository.establishConnection();
            clientRepository.update(toUpdate);
        } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                clientRepository.closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Optional<Client> findByEmailPassword(String email, String password) {
        try {
            clientRepository.establishConnection();
            Optional<Client> client = clientRepository.findByEmail(email);
            if(client.isPresent() && client.get().getPassword().equals(password)){
                return client;
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
