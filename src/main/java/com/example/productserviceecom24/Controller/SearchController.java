package com.example.productserviceecom24.Controller;

import com.example.productserviceecom24.DTOs.FilterDto;
import com.example.productserviceecom24.DTOs.GetProductDto;
import com.example.productserviceecom24.DTOs.SearchResponseDto;
import com.example.productserviceecom24.DTOs.SortingCriteria;
import com.example.productserviceecom24.Models.Product;
import com.example.productserviceecom24.Services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/")
    public SearchResponseDto seacrch(@RequestParam("query") String query,
                                     @RequestParam("filters")List<FilterDto> filters,
                                     @RequestParam("sortBy")SortingCriteria sortingCriteria,
                                     @RequestParam("pageNumber") int pageNumber,
                                     @RequestParam("pageSize") int pageSize) {
        SearchResponseDto searchResponseDto = new SearchResponseDto();
        Page<Product> productPage = searchService.search(query, filters, sortingCriteria,
                                                         pageNumber, pageSize);

        List<GetProductDto> getProductDtos = productPage.getContent().stream()
                .map(GetProductDto::from)
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(productPage.getNumber(),productPage.getSize());
        Page<GetProductDto> getProductDtoPage = new PageImpl<>(getProductDtos,pageable,productPage.getTotalElements());

        searchResponseDto.setGetProductsPage(getProductDtoPage);
        return searchResponseDto;
    }
}
