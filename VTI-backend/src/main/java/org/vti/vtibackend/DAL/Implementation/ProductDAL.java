package org.vti.vtibackend.DAL.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vti.vtibackend.DAL.Entity.Product;
import org.vti.vtibackend.DAL.Interface.IProductDAL;
import org.vti.vtibackend.DAL.Repository.ProductRepo;

import java.util.List;

@Repository
public class ProductDAL implements IProductDAL {

    private final ProductRepo productRepo;

    @Autowired
    public ProductDAL(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }
    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }
}
