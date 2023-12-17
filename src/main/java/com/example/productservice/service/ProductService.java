package com.example.productservice.service;

import com.example.productservice.dtos.ProductRequestDTO;
import com.example.productservice.dtos.ProductResponseDTO;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.RequestEntity.delete;

@Getter
@Setter
@Service
public class ProductService {
    private RestTemplateBuilder restTemplateBuilder;
    //private Map<Long,Object> fakeStoreProducts=new HashMap<>();
    private RedisTemplate<Long,Object> redisTemplate;
    public ProductService(RestTemplateBuilder restTemplateBuilder,RedisTemplate<Long,Object> redisTemplate){
        this.restTemplateBuilder=restTemplateBuilder;
        this.redisTemplate=redisTemplate;
    }
    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
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
//        if(fakeStoreProducts.containsKey(productId)){
//            return (Product) fakeStoreProducts.get(productId);
//        }
        Product redisProduct= (Product) redisTemplate.opsForHash().get(productId,"PRODUCTS");

        if(redisProduct!=null){
            return redisProduct;
        }
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
        //fakeStoreProducts.put(productId,product);
        redisTemplate.opsForHash().put(productId,"PRODUCTS",product);
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
        //RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<ProductRequestDTO> response=requestForEntity(HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{productId}",
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
    public Product deleteProduct(Long productId ){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<ProductRequestDTO> response=requestForEntity(HttpMethod.DELETE,"https://fakestoreapi.com/products/{productId}",null,
                ProductRequestDTO.class,productId);

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
    public Product replaceProduct(Long productId,ProductRequestDTO requestDTO){
        ResponseEntity<ProductRequestDTO> response=requestForEntity(HttpMethod.PUT,
                "https://fakestoreapi.com/products/{productId}",
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
