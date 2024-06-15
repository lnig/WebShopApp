package org.example.shopapp.Repository;

import org.example.shopapp.Model.Data.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}