package com.example.productserviceecom24.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class SearchResponseDto {
    private Page<GetProductDto> getProductsPage;
}
