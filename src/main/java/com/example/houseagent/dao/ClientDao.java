package com.example.houseagent.dao;

import com.example.houseagent.entity.Client;

import java.util.List;

public interface ClientDao {
    void addClient(Client client);
    List<Client> getAllClients();
}
