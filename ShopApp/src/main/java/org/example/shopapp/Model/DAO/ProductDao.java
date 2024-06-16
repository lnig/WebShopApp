package org.example.shopapp.Model.DAO;

import org.example.shopapp.Model.Data.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAllProducts();
}
