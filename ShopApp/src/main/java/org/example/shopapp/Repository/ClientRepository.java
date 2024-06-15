package org.example.shopapp.Repository;

import org.example.shopapp.Model.Data.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}