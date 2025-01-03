package org.vti.vtibackend.DAL.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vti.vtibackend.DAL.Entity.Product;
import org.vti.vtibackend.model.Product.ProductDTO;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "product_id", target = "product_id")
    ProductDTO ToDTO(Product product);

    @Mapping(source = "product_id", target = "product_id")
    Product ToEntity(ProductDTO productDTO);
}
