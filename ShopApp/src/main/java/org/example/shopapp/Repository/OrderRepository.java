package org.example.shopapp.Repository;

import org.example.shopapp.Model.Data.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}