package org.example.shopapp.Repository;

import org.example.shopapp.Model.Data.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByClientId(Integer clientId);
}