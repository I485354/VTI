package org.vti.vtibackend.DAL.Interface;

import org.vti.vtibackend.DAL.Entity.Product;

import java.util.List;

public interface IProductDAL {
    Product save(Product product);
    List<Product> findAll();
}
