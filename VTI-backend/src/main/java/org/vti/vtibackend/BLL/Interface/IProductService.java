package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.model.ProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO createProduct(ProductDTO product);
}
