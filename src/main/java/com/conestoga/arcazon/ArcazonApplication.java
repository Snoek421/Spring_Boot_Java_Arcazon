package com.conestoga.arcazon;

import com.conestoga.arcazon.model.Product;
import com.conestoga.arcazon.repository.ProductRepository;
import com.conestoga.arcazon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.conestoga.arcazon.model")
@EnableJpaRepositories("com.conestoga.arcazon.repository")
@ComponentScan( basePackages = "com.conestoga.arcazon.service, com.conestoga.arcazon.controller" )
public class ArcazonApplication implements CommandLineRunner {

    private ProductRepository productRepo = null;

    @Autowired
    public void ArcazonApplication(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ArcazonApplication.class, args);

        ProductService productService = (ProductService) ctx.getBean("productService");

        productService.test();
    }

    @Override
    public void run(String[] args) throws Exception {
        // Fetch all books
        System.out.println("Listing all products:");
        productRepo.findAll().forEach((Product p) -> System.out.println(p.toString()));
        System.out.println("~~~~~~~DONE!~~~~~~\n");
    }

}
