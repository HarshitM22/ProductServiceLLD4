package com.example.productservice.service;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Getter
@Setter
@Service
public class SelfCategoryService {
    private CategoryRepository categoryRepository;
    public SelfCategoryService(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    public List<Category>  findAllCategory(){
        return categoryRepository.findAllBy();
    }
    public List<Product> findAllProductInCategory(String Category){
        return categoryRepository.getProductInSpecificCategory(Category);
    }
}
