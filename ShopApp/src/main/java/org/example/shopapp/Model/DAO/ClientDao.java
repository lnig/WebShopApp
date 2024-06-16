package org.example.shopapp.Model.DAO;

import org.example.shopapp.Model.Data.Client;

import java.util.List;

public interface ClientDao {
    Client getClientByEmailAndPassword(String email, String password);
    Client getClientByEmail(String email);
    List<Client> getAllClients();
    void saveClient(Client client);
    void updateClient(Client client);
    void deleteClient(Client client);
}
