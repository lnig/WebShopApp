package org.example.shopapp.Model.DAO;

import org.example.shopapp.Model.Data.Client;
import org.example.shopapp.Repository.ClientRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao {

    private final ClientRepository clientRepository;
    public ClientDaoImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getClientByEmailAndPassword(String email, String password) {
        Client client = clientRepository.findClientByEmailAndPassword(email,password);
        return client;
    }
    @Override
    public Client getClientByEmail(String email) {
        Client client = clientRepository.findClientByEmail(email);
        return client;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void saveClient(Client client){
        clientRepository.save(client);
    }

    @Override
    public void updateClient(Client user) {
    }

    @Override
    public void deleteClient(Client user) {
    }
}
