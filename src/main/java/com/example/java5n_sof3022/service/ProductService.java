package com.example.java5n_sof3022.service;

import com.example.java5n_sof3022.entity.Product;
import com.example.java5n_sof3022.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public void saveProduct(@Valid Product product) {

        productRepository.save(product);
    }
}
