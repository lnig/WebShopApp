package org.example.shopapp.Repository;

import org.example.shopapp.Model.Data.Administrator;
import org.example.shopapp.Model.Data.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
    Administrator findAdministratorByEmailAndPassword(String email, String password);
    List<Administrator> findAll();

}