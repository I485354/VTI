package org.vti.vtibackend.DAL.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vti.vtibackend.BLL.Interface.IProductDAL;
import org.vti.vtibackend.DAL.Entity.Product;
import org.vti.vtibackend.DAL.Mapper.ProductMapper;
import org.vti.vtibackend.DAL.Repository.ProductRepo;
import org.vti.vtibackend.model.Product.ProductDTO;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductDAL implements IProductDAL {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    @Autowired
    public ProductDAL(ProductRepo productRepo, ProductMapper productMapper) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = productMapper.ToEntity(productDTO);
        Product savedProduct = productRepo.save(product);
        return productMapper.ToDTO(savedProduct);
    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepo.findAll()
                .stream()
                .map(productMapper::ToDTO)
                .collect(Collectors.toList());
    }
}
