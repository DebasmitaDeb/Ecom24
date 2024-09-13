package com.example.productserviceecom24.Controller;

import com.example.productserviceecom24.Client.AuthDtos.Role;
import com.example.productserviceecom24.Client.AuthDtos.ValidateTokenResponseDto;
import com.example.productserviceecom24.Client.AuthDtos.sessionStatus;
import com.example.productserviceecom24.Client.AuthenticationClient;
import com.example.productserviceecom24.DTOs.CreateProductDto;
import com.example.productserviceecom24.Models.Categories;
import com.example.productserviceecom24.Models.Product;
import com.example.productserviceecom24.Services.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;
    private RestTemplate restTemplate;
    AuthenticationClient authenticationClient;

    public ProductController(@Qualifier("SelfProductService") ProductService productService, RestTemplate restTemplate , AuthenticationClient authenticationClient) {
        this.productService = productService;
        this.restTemplate = restTemplate;
        this.authenticationClient = authenticationClient;
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
    public ResponseEntity<List<Product>> getAllProducts(@Nullable @RequestHeader("Auth_Token") String token,
                                                        @Nullable @RequestHeader("User_Id") Long userId){
        if(token == null|| userId== null){ return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); }

        ValidateTokenResponseDto response = authenticationClient.validate(token, userId);
        if (response.getSessionstatus().equals(sessionStatus.INVALID)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        boolean isUserAdmin = false;
        for(Role role : response.getUserDto().getRoles()){
            if(role.getRole().equals("ADMIN")){isUserAdmin = true;}
        }
        if(!isUserAdmin){return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);}
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>( products, HttpStatus.OK);
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
