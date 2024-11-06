package org.vti.vtibackend.Presentatie.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vti.vtibackend.BLL.Interface.IProductService;
import org.vti.vtibackend.BLL.Service.ProductService;
import org.vti.vtibackend.model.ProductDTO;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductDTO products) {
        return productService.createProduct(products);
    }
}