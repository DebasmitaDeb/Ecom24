package com.example.productserviceecom24.Services.SortingService;

import com.example.productserviceecom24.Models.Product;

import java.util.List;

public interface Sorter {
    List<Product> sort(List<Product> products);
}
