package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import service.CategoryService;

@RestController
public class CategoryController {

    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }
    @GetMapping("/products/categories")
    public String getAllCategory(){
        return "getting all category";
    }
    @GetMapping("/products/categories/{category}")
    public String getProductsInSpecificCategory(@PathVariable("category") String category){
        return "getting all product with category"+category;
    }
}
