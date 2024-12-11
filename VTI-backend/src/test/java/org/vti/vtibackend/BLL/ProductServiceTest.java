package org.vti.vtibackend.BLL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vti.vtibackend.BLL.Interface.IProductDAL;
import org.vti.vtibackend.BLL.Service.ProductService;
import org.vti.vtibackend.model.Product.ProductDTO;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    @Mock
    private IProductDAL productDAL;

    @InjectMocks
    private ProductService productService;

    private ProductDTO productDTO;
    private ProductDTO productDTO1;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        productDTO = new ProductDTO(1, "APK", "APK keuring", 10.0, 2, 21);
        productDTO1 = new ProductDTO(2, "Olie", "Olie verversen", 20.0, 5, 21);
    }

    @Test
    void getAllProducts() {
        // Arrange
        when(productDAL.findAll()).thenReturn(Arrays.asList(productDTO, productDTO1));

        // Act
        List<ProductDTO> products = productService.getAllProducts();

        // Assert
        assertThat(products).hasSize(2);
        assertThat(products.get(0)).isEqualTo(productDTO);
        assertThat(products.get(1)).isEqualTo(productDTO1);

        verify(productDAL, times(1)).findAll();
    }

    @Test
    void createProduct() {
        // Arrange
        when(productDAL.save(productDTO)).thenReturn(productDTO);

        // Act
        ProductDTO createdProduct = productService.createProduct(productDTO);

        // Assert
        assertThat(createdProduct).isNotNull();
        assertThat(createdProduct.getProduct_id()).isEqualTo(1);
        assertThat(createdProduct.getName()).isEqualTo("APK");
        assertThat(createdProduct.getPrice()).isEqualTo(10.0);

        verify(productDAL, times(1)).save(productDTO);
    }

    @Test
    void createProduct_NullInput() {

        assertThatThrownBy(() -> productService.createProduct(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Product cannot be null");
        verify(productDAL, never()).save(any());
    }

    @Test
    void getAllProducts_EmptyList() {

        when(productDAL.findAll()).thenReturn(Arrays.asList());

        List<ProductDTO> products = productService.getAllProducts();

        assertThat(products).isEmpty();
        verify(productDAL, times(1)).findAll();
    }
}
