package com.conestoga.arcazon.controller;

import com.conestoga.arcazon.dto.ProductDTO;
import com.conestoga.arcazon.model.Product;
import com.conestoga.arcazon.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }


    // GET /api/products - get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    // GET /api/products/{id} - get individual product by id
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    // POST /api/products - add new product
    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO product) {
        System.out.println(product.toString());
        if (product != null && product.getCategoryId() != null && product.getCategoryId() > 0) {
            long categoryId = product.getCategoryId();
            Product productEntity = product.toEntity();
            return productService.createProduct(productEntity, categoryId).toProductDTO();
        } else {
            System.out.println("Category can't be null, Category ID can't be null, and category ID must be a valid ID...");
            return null;
        }
    }

    @GetMapping("/{min}/{max}")
    public List<Product> findByPriceBetween(@PathVariable Double min, @PathVariable Double max) {
        List<Product> productList;
        productList = productService.findByPriceBetween(min, max);

        return productList;
    }


}
