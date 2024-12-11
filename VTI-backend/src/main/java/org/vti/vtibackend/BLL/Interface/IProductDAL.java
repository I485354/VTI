package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.model.Product.ProductDTO;

import java.util.List;

public interface IProductDAL {
    ProductDTO save(ProductDTO product);
    List<ProductDTO> findAll();
}
