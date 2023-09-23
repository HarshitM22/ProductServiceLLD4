package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private String name;
    private double price;
    private String description;
    private Category category;
    private String image;

}
