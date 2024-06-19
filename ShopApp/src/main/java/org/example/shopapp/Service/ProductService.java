package org.example.shopapp.Service;

import org.example.shopapp.Model.DAO.ProductDao;
import org.example.shopapp.Model.Data.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllProducts(){
        return productDao.getAllProducts();
    }

    public Product getProductById(Integer id) {
        return productDao.findById(id);
    }

    public void saveProduct(Product product) {
        productDao.save(product);
    }

    public void deleteProductById(Integer id) {
        productDao.deleteById(id);
    }
}
