package org.vti.vtibackend.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vti.vtibackend.model.Products;

public interface Product extends JpaRepository<Products, Integer> {
}
