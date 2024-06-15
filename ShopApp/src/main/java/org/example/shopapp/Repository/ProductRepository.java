package org.example.shopapp.Repository;

import org.example.shopapp.Model.Data.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}