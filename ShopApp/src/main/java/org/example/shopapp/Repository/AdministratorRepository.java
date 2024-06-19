package org.example.shopapp.Repository;

import org.example.shopapp.Model.Data.Administrator;
import org.example.shopapp.Model.Data.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
    Administrator findAdministratorByEmailAndPassword(String email, String password);
    Administrator findAdministratorByEmail(String email);
    List<Administrator> findAll();

}