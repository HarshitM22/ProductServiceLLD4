package com.example.productservice.controllers;

import com.example.productservice.AuthenticationClient.AuthenticationClient;
import com.example.productservice.AuthenticationClient.ProductServiceDtos.Role;
import com.example.productservice.AuthenticationClient.ProductServiceDtos.SessionStatus;
import com.example.productservice.AuthenticationClient.ProductServiceDtos.ValidateTokenResponseDto;
import com.example.productservice.dtos.ProductRequestDTO;
import com.example.productservice.models.Product;
import com.example.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;
    private AuthenticationClient authenticationClient;
    public ProductController(ProductService productService,AuthenticationClient authenticationClient){
        this.productService=productService;
        this.authenticationClient=authenticationClient;
    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(@Nullable @RequestHeader("AUTH_TOKEN") String token,
                                                       @Nullable @RequestHeader("USER_ID") Long user_id){
//        if(token==null||user_id==null){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        ValidateTokenResponseDto response=authenticationClient.validate(token,user_id);
//        if(response.getSessionStatus().equals(SessionStatus.INVALID)){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        boolean isUserAdmin=false;
//        for(Role role:response.getUserDto().getRoles()){
//            if(role.getName().equals("ADMIN")){
//                isUserAdmin=true;
//            }
//        }
//        if(!isUserAdmin){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
        List<Product> list=productService.getAllProduct();
        return new ResponseEntity<>(list, HttpStatus.OK);
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
