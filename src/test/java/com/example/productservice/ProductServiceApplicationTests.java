package com.example.productservice;

import com.example.productservice.inheritanceExample.tablePerClass.Mentor;
import com.example.productservice.inheritanceExample.tablePerClass.TpcMentorRepository;
import com.example.productservice.inheritanceExample.tablePerClass.TpcUserRepository;
import com.example.productservice.inheritanceExample.tablePerClass.User;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceApplicationTests {
//    @Autowired
//    private TpcUserRepository tpcUserRepository;
//    @Autowired
//    private TpcMentorRepository tpcMentorRepository;
//    @Autowired
//    private ProductRepository productRepository;
//    @Autowired
//    private CategoryRepository categoryRepository;
//    @Test
//    void contextLoads() {
//    }

   /* @Test
    void testDifferentInheritances(){
        /*User user=new User();
        user.setEmail("harshit@gmail.com");
        user.setPassword("9425793367");
        tpcUserRepository.save(user);
        Mentor mentor=new Mentor();
        mentor.setEmail("chegg@gmail.com");
        mentor.setPassword("123456");
        mentor.setNoOfMentee(2);
        mentor.setNoOfSession(12);
        tpcMentorRepository.save(mentor);*/
   // }

    //@Test

    /*void savingProductAndCategory(){
        Category category=new Category();
        category.setName("clothes");
        category.setDescription("BoysClothing");
        Product product=new Product();
        product.setCategory(category);
        product.setTitle("denimShort");
        product.setPrice(549);
        product.setImage("HRXImage");
        productRepository.save(product);
    }*/
//    @Test
//    void fetchProduct(){
//        Product product=productRepository.findProductById(2L);
//        System.out.print(product.getTitle());
//        Category category=product.getCategory();
//    }

}
