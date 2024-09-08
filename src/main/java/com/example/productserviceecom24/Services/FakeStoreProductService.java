package com.example.productserviceecom24.Services;

import com.example.productserviceecom24.DTOs.FakeStoreProductDto;
import com.example.productserviceecom24.Models.Categories;
import com.example.productserviceecom24.Models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) {

        FakeStoreProductDto fakeStoreProduct = restTemplate.getForObject("https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class);
        return fakeStoreProduct.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProducts = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        List<Product> allproducts = new ArrayList<>();
            for(FakeStoreProductDto fakeStoreProduct: fakeStoreProducts) {
                allproducts.add(fakeStoreProduct.toProduct());
            }
            return allproducts;
    }

    @Override
    public Product createNewProduct(String title,
                                    double price,
                                    String category,
                                    String description,
                                    String image){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(image);

        FakeStoreProductDto response = restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStoreProductDto,  //request body
                FakeStoreProductDto.class); //data type of response
        return response.toProduct();
    }

    @Override
    public List<Categories> getAllCategories(){
        Categories[] response = restTemplate.getForObject("https://fakestoreapi.com/products/categories",
                Categories[].class);
        List<Categories> allcategories = new ArrayList<>();
        for(Categories category: response) {
            allcategories.add(category);
        }
        return allcategories;
    }

    @Override
    public List<Product> getAllProductsinCategory(String categoryName) {
        FakeStoreProductDto[] fakeStorecategoryProducts = restTemplate.getForObject("https://fakestoreapi.com/products/category/"
                + categoryName,
                FakeStoreProductDto[].class);
        List<Product> allproductsincategory = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProduct: fakeStorecategoryProducts) {
            allproductsincategory.add(fakeStoreProduct.toProduct());
        }
        return allproductsincategory;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }


    @Override
    public Product deleteProduct(Long productID) {
        return null;
    }
}
