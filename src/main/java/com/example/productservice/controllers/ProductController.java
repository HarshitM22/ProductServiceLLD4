package com.example.productservice.controllers;

import com.example.productservice.dtos.ProductRequestDTO;
import com.example.productservice.models.Product;
import com.example.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProduct();
    }
    @GetMapping("/products/{productId}")
    public Product getSingleProduct(@PathVariable("productId") Long productId){
        //return "returning single product with productId"+productId;
        Product product=productService.getSingleProduct(productId);
    return product;
    }
    @PostMapping("/products")
    public Product addSingleProduct(@RequestBody ProductRequestDTO requestDTO){

        return productService.addSingleProduct(requestDTO);
    }
    @PatchMapping("/products/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId,@RequestBody ProductRequestDTO requestDTO){
        return productService.updateProduct(productId, requestDTO);
    }
    @DeleteMapping("/products/{productId}")
    public Product deleteProduct(@PathVariable("productId") Long productId){
        return productService.deleteProduct(productId);
    }
    @PutMapping("/products/{productId}")
    public Product replaceProduct(@PathVariable("productId") Long productId,@RequestBody ProductRequestDTO requestDTO){
        return productService.replaceProduct(productId,requestDTO);
    }

}
