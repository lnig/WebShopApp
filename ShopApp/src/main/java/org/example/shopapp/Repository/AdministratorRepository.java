package org.example.shopapp.Repository;

import org.example.shopapp.Model.Data.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
}