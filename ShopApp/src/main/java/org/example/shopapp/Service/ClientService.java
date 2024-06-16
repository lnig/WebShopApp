package org.example.shopapp.Service;

import org.example.shopapp.Model.DAO.ClientDao;
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

    public Client getClient(String email, String password){
        return clientDao.getClient(email,password);
    }
}
