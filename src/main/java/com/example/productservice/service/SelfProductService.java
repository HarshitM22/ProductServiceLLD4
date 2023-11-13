package com.example.productservice.service;

import com.example.productservice.dtos.ProductRequestDTO;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class SelfProductService {
    private ProductRepository productRepository;
    public SelfProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    public List<Product> getAllProduct(){

        return productRepository.findAllBy();
    }
    public Product getSingleProduct(Long productId){
        return productRepository.findProductById(productId);
    }
    public Product addSingleProduct(ProductRequestDTO productRequestDTO){
        Product product=new Product();
        product.setId(productRequestDTO.getId());
        product.setTitle(productRequestDTO.getTitle());
        product.setPrice(productRequestDTO.getPrice());
        Category category=new Category();
        category.setName(productRequestDTO.getCategory());
        product.setCategory(category);
        product.setImage(productRequestDTO.getImage());
        return productRepository.save(product);
    }
    public Product updateProduct(ProductRequestDTO productRequestDTO,Long id){
        Product product=new Product();
        product.setId(productRequestDTO.getId());
        product.setTitle(productRequestDTO.getTitle());
        product.setPrice(productRequestDTO.getPrice());
        Category category=new Category();
        category.setName(productRequestDTO.getCategory());
        product.setCategory(category);
        product.setImage(productRequestDTO.getImage());
        return productRepository.save(product);
    }
    public Product replaceProduct(ProductRequestDTO productRequestDTO,Long id){
        Product product=new Product();
        product.setId(productRequestDTO.getId());
        product.setTitle(productRequestDTO.getTitle());
        product.setPrice(productRequestDTO.getPrice());
        Category category=new Category();
        category.setName(productRequestDTO.getCategory());
        product.setCategory(category);
        product.setImage(productRequestDTO.getImage());
        return productRepository.save(product);
    }
    public Product deleteProduct(Long id){
        return productRepository.removeProductById(id);
    }
}
