package org.example.shopapp.Controller;


import ch.qos.logback.core.model.Model;
import org.example.shopapp.Model.Data.Product;
import org.example.shopapp.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ModelAndView getAllProducts(){
        ModelAndView modelAndView = new ModelAndView();
        List<Product> products = productService.getAllProducts();
        modelAndView.setViewName("productsPage");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
