package com.example.productserviceecom24.Services;

import com.example.productserviceecom24.DTOs.FilterDto;
import com.example.productserviceecom24.DTOs.SortingCriteria;
import com.example.productserviceecom24.Models.Product;
import com.example.productserviceecom24.Repositories.ProductRepository;
import com.example.productserviceecom24.Services.FilteringService.FilterFactory;
import com.example.productserviceecom24.Services.SortingService.SorterFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Page<Product> search(String query,
                                List<FilterDto> filters,
                                SortingCriteria sortingCriteria,
                                int pageNumber, //1    //2
                                int pageSize) { //5    //5 -> from ->(5 * (pagenumber-1)) to ->(pagenumber * pagesize) -1
        List<Product> products = productRepository.findByTitleContaining(query);

        for(FilterDto filterDto: filters) {
            products = FilterFactory.getFilterFromkey(
                    filterDto.getKey()).apply(products, filterDto.getValues());
        }
        products = SorterFactory.getSortedByValue(sortingCriteria).sort(products);

        List<Product> productsOnPage = new ArrayList<>();
        for(int i = pageSize *(pageNumber - 1); i < (pageNumber * pageSize); i++ ) {
            productsOnPage.add(products.get(i));
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return new PageImpl<>(productsOnPage, pageable, products.size());

    }
}

