package com.example.productserviceecom24.Services.FilteringService;

public class FilterFactory {

    public static Filter getFilterFromkey(String key){
        return switch(key){
            case "ram" -> new RAMFilter();
            case "brand" -> new BrandFilter();
            case null, default -> null;
        };
    }
}
