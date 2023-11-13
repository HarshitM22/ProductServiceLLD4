package com.example.productservice.repositories;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category save(Category category);
    Category findCategoryById(Long id);
    List<Category> findAllBy();
    @Query(value = "select p from product p where p.category.name=:Category", nativeQuery = true)
    List<Product> getProductInSpecificCategory(String Category);

}
