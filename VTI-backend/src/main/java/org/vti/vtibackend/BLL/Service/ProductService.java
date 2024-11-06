package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.BLL.Interface.IProductService;
import org.vti.vtibackend.BLL.Mapper.ProductMapper;
import org.vti.vtibackend.DAL.Entity.Product;
import org.vti.vtibackend.DAL.Interface.IProductDAL;
import org.vti.vtibackend.DAL.Repository.ProductRepo;
import org.vti.vtibackend.model.ProductDTO;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    private final IProductDAL productDAL;
    private final ProductMapper productMapper;
    @Autowired
    public ProductService(ProductMapper productMapper, IProductDAL productDAL) {
        this.productMapper = productMapper;
        this.productDAL = productDAL;
    }
    public List<ProductDTO> getAllProducts() {
          List<Product> products = productDAL.findAll();
          return products.stream()
                  .map(productMapper::ToDTO)
                    .collect(Collectors.toList());
    }

    public ProductDTO createProduct(ProductDTO product) {
        Product products = productMapper.ToEntity(product);
        Product savedProduct = productDAL.save(products);
        return productMapper.ToDTO(savedProduct);
    }
}