package dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import models.Category;

@Getter
@Setter
@ToString
public class ProductRequestDTO {
    private String name;
    private double price;
    private String description;
    private String category;
    private String image;
}
