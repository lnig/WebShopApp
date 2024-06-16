package org.example.shopapp.Service;

import org.example.shopapp.Model.DAO.ClientDao;
import org.example.shopapp.Model.DTO.User;
import org.example.shopapp.Model.Data.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientDao clientDao;
    @Autowired
    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public Client getClientByEmailAndPassword(String email, String password){
        return clientDao.getClientByEmailAndPassword(email,password);
    }

    public Client getClientByEmail(String email){
        return clientDao.getClientByEmail(email);
    }

    public Client mapUserToClient(User user){
        Client client = new Client();
        client.setName(user.getName());
        client.setSurname(user.getSurname());
        client.setEmail(user.getEmail());
        client.setPassword(user.getPassword());
        return client;
    }

    public void saveClient(Client client){
        clientDao.saveClient(client);
    }
}
