package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.vti.vtibackend.BLL.Interface.IProductDAL;
import org.vti.vtibackend.model.Product.ProductDTO;

import java.util.List;


@Service
public class ProductService{
    private final IProductDAL productDAL;

    @Autowired
    public ProductService( IProductDAL productDAL) {

        this.productDAL = productDAL;
    }
    public List<ProductDTO> getAllProducts() {
        return productDAL.findAll();

    }

    public ProductDTO createProduct(ProductDTO product) {
        if(product == null) {
            throw new NullPointerException("Product cannot be null");
        }
       return productDAL.save(product);
    }
}