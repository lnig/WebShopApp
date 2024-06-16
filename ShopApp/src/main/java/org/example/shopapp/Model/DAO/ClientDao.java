package org.example.shopapp.Model.DAO;

import org.example.shopapp.Model.Data.Client;

import java.util.List;

public interface ClientDao {
    Client getClient(String email, String password);
    List<Client> getAllClients();
    void updateClient(Client client);
    void deleteClient(Client client);
}
