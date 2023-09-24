package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductRequestDTO {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
}
