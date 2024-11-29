package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.DAL.Entity.Product;
import org.vti.vtibackend.model.ProductDTO;

import java.util.List;

public interface IProductDAL {
    ProductDTO save(ProductDTO product);
    List<ProductDTO> findAll();
}
