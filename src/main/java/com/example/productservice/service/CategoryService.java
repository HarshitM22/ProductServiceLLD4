package com.example.productservice.service;

import com.example.productservice.dtos.CategoryDTO;
import com.example.productservice.dtos.ProductRequestDTO;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    RestTemplateBuilder restTemplateBuilder;
    public CategoryService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }
    public List<Category> getAllCategory(){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<CategoryDTO[]> response= restTemplate.getForEntity("https://fakestoreapi.com/products/categories",
                CategoryDTO[].class);
        List<Category> allCategory=new ArrayList<>();
        for(CategoryDTO categoryDTO: response.getBody()){
            Category category=new Category();
            category.setName(categoryDTO.getCategory());
            allCategory.add(category);
        }
        return allCategory;
    }
    public List<Product> getProductInSpecificCategory(String category){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<ProductRequestDTO[]> response=restTemplate.getForEntity("https://fakestoreapi.com/products/category/{categories}",
                ProductRequestDTO[].class,
                category);
        List<Product> allProduct=new ArrayList<>();
        for(ProductRequestDTO requestDTO:response.getBody()){
            Product product =new Product();
            product.setId(requestDTO.getId());
            product.setTitle(requestDTO.getTitle());
            product.setPrice(requestDTO.getPrice());
            Category categori=new Category();
            categori.setName(requestDTO.getCategory());
            product.setCategory(categori);
            product.setImage(requestDTO.getImage());
            allProduct.add(product);
        }
    return allProduct;
    }
}
