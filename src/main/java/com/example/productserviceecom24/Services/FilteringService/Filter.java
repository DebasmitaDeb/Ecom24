package com.example.productserviceecom24.Services.FilteringService;

import com.example.productserviceecom24.Models.Product;

import java.util.List;

public interface Filter {
    public List<Product> apply(List<Product> products,
                               List<String> allowedValues);
}
