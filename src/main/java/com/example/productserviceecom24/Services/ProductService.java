package com.example.productserviceecom24.Services;

import com.example.productserviceecom24.Models.Categories;
import com.example.productserviceecom24.Models.Product;

import java.util.List;

public interface ProductService {

     Product getSingleProduct(Long productID);

     List<Product> getAllProducts();

     Product createNewProduct(
             String title,
             double price,
             String category,
             String description,
             String image
    );

     List<Categories> getAllCategories();
     List<Product> getAllProductsinCategory(String categoryname);
     Product updateProduct(Long productID,Product product);
     Product deleteProduct(Long productID);
}
