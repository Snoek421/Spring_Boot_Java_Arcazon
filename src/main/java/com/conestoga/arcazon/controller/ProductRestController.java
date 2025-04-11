package com.conestoga.arcazon.controller;

import com.conestoga.arcazon.model.Product;
import com.conestoga.arcazon.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
