package com.example.productserviceecom24.Controller;

import com.example.productserviceecom24.DTOs.CreateProductDto;
import com.example.productserviceecom24.Models.Categories;
import com.example.productserviceecom24.Models.Product;
import com.example.productserviceecom24.Services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;
    private RestTemplate restTemplate;

    public ProductController(@Qualifier("SelfProductService") ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

//    POST/products
//    request body{ Product metadata}
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductDto request) {
        return productService.createNewProduct(
                request.getTitle(),
                request.getPrice(),
                request.getCategory(),
                request.getDescription(),
                request.getImage()
        );
    }

//    GET/products/id
    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long productID){
         return productService.getSingleProduct(productID);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){

        return productService.getAllProducts();
    }

    @GetMapping("/categories")
    public List<Categories> getAllCategories(){

        return productService.getAllCategories();
    }

    @GetMapping("/categories/{name}")
    public List<Product> getAllProductsinCategory(@PathVariable("name") String categoryname){
        return productService.getAllProductsinCategory(categoryname);
    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable("id") Long productID, @RequestBody Product product){
        return productService.updateProduct(productID, product);
    }

    @PutMapping("/delete/{id}")
    public Product deleteProduct(@PathVariable("id") Long productID){
        return productService.deleteProduct(productID);
    }
}
