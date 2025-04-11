package com.conestoga.arcazon.controller;


import com.conestoga.arcazon.model.Product;
import com.conestoga.arcazon.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /products - get all products
    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products"; // maps to a products.html file
    }

    // GET /products/{id} - get individual product by id
    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product-details"; // maps to a product-details.html file
    }

    // POST /products - create a product
    public void createProduct(Model model){
    }

    // PUT /products/{id} - update a product
    public void updateProduct(Model model) {

    }

    // DELETE /products/{id} - delete a product
    public void deleteProduct(Model model) {

    }
}
