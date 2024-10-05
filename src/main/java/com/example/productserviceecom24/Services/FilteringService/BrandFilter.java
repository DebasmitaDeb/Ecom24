package com.example.productserviceecom24.Services.FilteringService;

import com.example.productserviceecom24.Models.Product;

import java.util.List;

public class BrandFilter implements Filter {

    @Override
    public List<Product> apply(List<Product> products, List<String> allowedValues) {
        return List.of();
    }
}
