package com.example.productserviceecom24.Services;

import com.example.productserviceecom24.Models.Categories;
import com.example.productserviceecom24.Models.Product;
import com.example.productserviceecom24.Repositories.CategoryRepository;
import com.example.productserviceecom24.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long productID) {

        return productRepository.findByIdIs(productID);
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product createNewProduct(String title, double price, String category, String description, String image) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageurl(image);

        Categories category1 = categoryRepository.findByTitle(category);
        if(category1 == null) {
            category1 = new Categories();
            category1.setTitle(category);
        }
        product.setCategory(category1);
        Product product1 = productRepository.save(product);
        return product1;
    }

    @Override
    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsinCategory(String categoryname) {
        return productRepository.findAllByCategory_TitleIsLike(categoryname);
    }

    @Override
    public Product updateProduct(Long productID, Product newProduct) {
        Optional<Product> existingProduct = productRepository.findById(productID);
        if(existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setTitle(newProduct.getTitle());
            updatedProduct.setPrice(newProduct.getPrice());
            updatedProduct.setDescription(newProduct.getDescription());
            updatedProduct.setImageurl(newProduct.getImageurl());
            Categories category1 = categoryRepository.findByTitle(newProduct.getCategory().getTitle());
                if (category1 == null) {
                    category1 = new Categories();
                    category1.setTitle(newProduct.getCategory().getTitle());
                }
                updatedProduct.setCategory(category1);
            return productRepository.save(updatedProduct);
        }
        return null;
    }

    @Override
    public Product deleteProduct(Long productID) {
        Product deletedProduct = productRepository.findByIdIs(productID);
        deletedProduct.setDeleted(true);
        return deletedProduct;
    }
}
