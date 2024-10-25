package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.DAL.Repository.Product;
import org.vti.vtibackend.model.Products;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private Product productRepository;

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public Products createProduct(Products product) {
        return productRepository.save(product);
    }
}