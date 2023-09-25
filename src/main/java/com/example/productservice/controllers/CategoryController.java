package com.example.productservice.controllers;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.productservice.service.CategoryService;

import java.util.List;

@Getter
@Setter
@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }
    @GetMapping("/products/categories")
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }
    @GetMapping("/products/categories/{category}")
    public List<Product> getProductsInSpecificCategory(@PathVariable("category") String category){
        return categoryService.getProductInSpecificCategory(category);
    }
}
