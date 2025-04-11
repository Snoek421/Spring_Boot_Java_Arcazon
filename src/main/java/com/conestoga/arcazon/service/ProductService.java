package com.conestoga.arcazon.service;

import com.conestoga.arcazon.model.Category;
import com.conestoga.arcazon.model.Product;
import com.conestoga.arcazon.repository.CategoryRepository;
import com.conestoga.arcazon.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;

    public ProductService(ProductRepository productRepo, CategoryRepository categoryRepo) {
        this.productRepo=productRepo;
        this.categoryRepo=categoryRepo;
    }

    public void test(){
        System.out.println("Test running to verify service");
        // List All Employees
        Iterable<Product> employees = productRepo.findAll();
        employees.forEach(prd -> System.out.println(prd));

        // Find an Employee by id
        Optional<Product> result = productRepo.findById(1L);
        result.ifPresent(prd -> System.out.println(prd));

        // Find an Employee by Last Name
        List<Product> products = productRepo.findByCategory_Id(1L);
        products.forEach(prd -> System.out.println(prd));

        // Count Number of Employees
        Long count = productRepo.count();
        System.out.println("Number of products are: " + count);

        // Delete an Employee Record
        //productRepo.deleteById(3L);

        // List All Employees
        Iterable<Product> products1 = productRepo.findAll();
        products1.forEach(prd -> System.out.println(prd));
    }


    //return all products from database
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product findById(long id) {
        return productRepo.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
    }

    // Create a new product
    public Product createProduct(Product product, @Nullable Long categoryId) {
        // Validate inputs
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (categoryId == null) {
            throw new IllegalArgumentException("Valid category ID is required");
        }

        //get category object by id
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found with id: "+categoryId));
        product.setCategory(category);

        //Create product
        return productRepo.save(product);
    }

    // Update a product
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepo.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));

        // Update fields
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());

        // update category
        if (productDetails.getCategory() != null && productDetails.getCategory().getId() != null) {
            Category category = categoryRepo.findById(productDetails.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + productDetails.getCategory().getId()));
            product.setCategory(category);
        }

        //update product
        return productRepo.save(product);
    }


    // Delete a product
    public void deleteProduct(long id) {
        Product product = findById(id); // This will throw exception if not found
        productRepo.delete(product);
    }


    // Find products within price range
    public List<Product> findByPriceBetween(Double minPrice, Double maxPrice) {
        return productRepo.findByPriceBetween(minPrice, maxPrice);
    }

    // Find products by category
    public List<Product> findByCategoryId(Long categoryId) {
        return productRepo.findByCategory_Id(categoryId);
    }

    // Find products by category
    public List<Product> findByCategoryName(String name) {
        return productRepo.findByCategory_Name(name);
    }
}
