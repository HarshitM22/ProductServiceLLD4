package com.example.productservice.service;

import com.example.productservice.dtos.ProductRequestDTO;
import com.example.productservice.dtos.ProductResponseDTO;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Service
public class ProductService {
    private RestTemplateBuilder restTemplateBuilder;
    public ProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }
    public List<Product> getAllProduct(){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<ProductRequestDTO[]> response=restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                ProductRequestDTO[].class
        );
        List<Product> allProduct=new ArrayList<>();
        for(ProductRequestDTO requestDTO:response.getBody()){
            Product product =new Product();
            product.setId(requestDTO.getId());
            product.setTitle(requestDTO.getTitle());
            product.setPrice(requestDTO.getPrice());
            Category category=new Category();
            category.setName(requestDTO.getCategory());
            product.setCategory(category);
            product.setImage(requestDTO.getImage());
            allProduct.add(product);
        }
    return allProduct;
    }
    public Product getSingleProduct(Long productId){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<ProductRequestDTO> response=restTemplate.getForEntity(
                "https://fakestoreapi.com/products/{id}",
                ProductRequestDTO.class,
                productId
        );
        ProductRequestDTO requestDTO=response.getBody();
        Product product =new Product();
        product.setId(requestDTO.getId());
        product.setTitle(requestDTO.getTitle());
        product.setPrice(requestDTO.getPrice());
        Category category=new Category();
        category.setName(requestDTO.getCategory());
        product.setCategory(category);
        product.setImage(requestDTO.getImage());
    return product;
    }
    public Product addSingleProduct(ProductRequestDTO requestDTO){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<ProductRequestDTO> response=restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                requestDTO,
                ProductRequestDTO.class);

        ProductRequestDTO updatedDTO=response.getBody();
        Product product =new Product();
        product.setId(updatedDTO.getId());
        product.setTitle(updatedDTO.getTitle());
        product.setPrice(updatedDTO.getPrice());
        Category category=new Category();
        category.setName(updatedDTO.getCategory());
        product.setCategory(category);
        product.setImage(updatedDTO.getImage());
    return product;
    }
    public Product updateProduct(Long productId,ProductRequestDTO requestDTO){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<ProductRequestDTO> response=restTemplate.postForEntity("https://fakestoreapi.com/products/{productId}",
        requestDTO,
        ProductRequestDTO.class,
        productId);

        ProductRequestDTO updatedDTO=response.getBody();
        Product product =new Product();
        product.setId(updatedDTO.getId());
        product.setTitle(updatedDTO.getTitle());
        product.setPrice(updatedDTO.getPrice());
        Category category=new Category();
        category.setName(updatedDTO.getCategory());
        product.setCategory(category);
        product.setImage(updatedDTO.getImage());
        return product;
    }
}
