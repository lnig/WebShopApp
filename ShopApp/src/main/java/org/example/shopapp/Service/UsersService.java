package org.example.shopapp.Service;

import org.example.shopapp.Model.DTO.User;
import org.example.shopapp.Model.Data.Administrator;
import org.example.shopapp.Model.Data.Client;
import org.example.shopapp.Repository.AdministratorRepository;
import org.example.shopapp.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    AdministratorRepository administratorRepository;

    @Autowired
    ClientRepository clientRepository;

    public void addAdministratorToDB(Administrator administrator){
        administratorRepository.save(administrator);
    }

    public void addClientToDB(Client client){
        clientRepository.save(client);
    }

    public Administrator mapToAdministrator(User user){
        Administrator administrator = new Administrator();
        administrator.setName(user.getName());
        administrator.setSurname(user.getSurname());
        administrator.setEmail(user.getEmail());
        administrator.setPassword(user.getPassword());
        return administrator;
    }

    public Client mapToClient(User user){
        Client client = new Client();
        client.setName(user.getName());
        client.setSurname(user.getSurname());
        client.setEmail(user.getEmail());
        client.setPassword(user.getPassword());
        return client;
    }


}
