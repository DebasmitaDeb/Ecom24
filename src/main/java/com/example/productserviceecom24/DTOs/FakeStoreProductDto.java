package com.example.productserviceecom24.DTOs;

import com.example.productserviceecom24.Models.Categories;
import com.example.productserviceecom24.Models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

    public Product toProduct(){
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setImageurl(image);
        product.setDescription(description);
        product.setPrice(price);

        Categories categories = new Categories();
        categories.setTitle(category);
        product.setCategory(categories);
        return product;
    }

//    public Categories toCategories(){
//        Categories categories = new Categories();
//        categories.setTitle(category);
//        return categories;
//    }
}
