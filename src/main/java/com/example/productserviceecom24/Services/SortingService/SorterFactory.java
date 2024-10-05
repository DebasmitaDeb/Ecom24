package com.example.productserviceecom24.Services.SortingService;

import com.example.productserviceecom24.DTOs.SortingCriteria;

public class SorterFactory {
    public static Sorter getSortedByValue(SortingCriteria sortingCriteria) {
        return switch (sortingCriteria){
            case Price_High_to_Low -> new PriceHitoLowSorter();
            case Price_Low_to_High -> null;
            case null, default -> null;
        };
    }
}
