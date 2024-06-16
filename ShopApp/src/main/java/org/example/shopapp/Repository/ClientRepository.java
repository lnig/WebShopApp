package org.example.shopapp.Repository;

import org.example.shopapp.Model.Data.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findClientByEmailAndPassword(String email, String password);
    Client findClientByEmail(String email);
    List<Client> findAll();

}