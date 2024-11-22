package org.vti.vtibackend.BLL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vti.vtibackend.BLL.Mapper.ProductMapper;
import org.vti.vtibackend.BLL.Service.ProductService;
import org.vti.vtibackend.DAL.Entity.Product;
import org.vti.vtibackend.DAL.Implementation.ProductDAL;
import org.vti.vtibackend.model.ProductDTO;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    @Mock
    private ProductDAL productDAL;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private Product product1;
    private ProductDTO productDTO;
    private ProductDTO productDTO1;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        product = new Product(1,"APK" ,"APK keuring" , 10.0, 2,21);
        product1 = new Product(2,"", "" , 20.0, 2,21);
        productDTO = new ProductDTO(1, "APK", "APK keuring" , 10.0, 2, 21);
        productDTO1 = new ProductDTO(2, "", "" , 20.0, 2, 21);
    }

    @Test
    void getAllProducts() {


        when(productDAL.findAll()).thenReturn(Arrays.asList(product, product1));


        when(productMapper.ToDTO(product)).thenReturn(productDTO);
        when(productMapper.ToDTO(product1)).thenReturn(productDTO1);

        List<ProductDTO> products = productService.getAllProducts();

        assertThat(products).hasSize(2);
        assertThat(products.get(0)).isEqualTo(productDTO);
        assertThat(products.get(1)).isEqualTo(productDTO1);

        verify(productDAL, times(1)).findAll();

    }
    @Test
    void createProduct() {
        when(productMapper.ToEntity(productDTO)).thenReturn(product);
        when(productDAL.save(product)).thenReturn(product);
        when(productMapper.ToDTO(product)).thenReturn(productDTO);


        ProductDTO createdProduct = productService.createProduct(productDTO);

        assertThat(createdProduct.getProduct_id()).isEqualTo(1);
        assertThat(createdProduct.getName()).isEqualTo("APK");
        assertThat(createdProduct.getPrice()).isEqualTo(10.0);


        verify(productMapper, times(1)).ToEntity(productDTO);
        verify(productDAL, times(1)).save(product);
        verify(productMapper, times(1)).ToDTO(product);

    }
}
