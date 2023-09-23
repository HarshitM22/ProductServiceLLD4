package controllers;

import dtos.ProductRequestDTO;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

@RestController
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @GetMapping("/products")
    public String getAllProducts(){
        return "getting all products";
    }
    @GetMapping("/products/{productId}")
    public String getSingleProduct(@PathVariable("productId") Long productId){
        return "returning single product with productId"+productId;
    }
    @PostMapping("/products")
    public String addProduct(@RequestBody ProductRequestDTO requestDTO){
        return "adding new product"+requestDTO;
    }
    @PutMapping("/products/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId,@RequestBody ProductRequestDTO requestDTO){
        return "updating product with productId"+productId+" "+requestDTO;
    }
    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId){
        return "deleting product with productId"+productId;
    }
}
