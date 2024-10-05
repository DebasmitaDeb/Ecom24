package com.example.productserviceecom24.Repositories;

import com.example.productserviceecom24.Models.Product;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);

    Product findByIdIs(Long id);

    @Override
    List<Product> findAll();

    List<Product> findAllByCategory_TitleIsLike(String categoryname);

    List<Product> findByTitleContaining(String query);
}
