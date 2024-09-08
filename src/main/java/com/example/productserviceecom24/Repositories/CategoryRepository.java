package com.example.productserviceecom24.Repositories;

import com.example.productserviceecom24.Models.Categories;
import com.example.productserviceecom24.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Categories, Long> {

    Categories findByTitle(String title);
    @Override
    List<Categories> findAll();

}
