package org.example.shopapp.Controller;

import org.example.shopapp.Model.Data.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<Orderitem, Integer> {
}