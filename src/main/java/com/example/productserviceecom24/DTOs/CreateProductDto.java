package com.example.productserviceecom24.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDto {

    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
