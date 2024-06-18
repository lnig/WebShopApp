package org.example.shopapp.Model.DAO;

import org.example.shopapp.Model.Data.Product;
import org.example.shopapp.Repository.ClientRepository;
import org.example.shopapp.Repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao{

    private final ProductRepository productRepository;
    public ProductDaoImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
