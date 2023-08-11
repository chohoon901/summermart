package com.example.demo.dto;

import com.example.demo.entity.Product;
import lombok.Data;

@Data
public class ProductDto {
    private int stock;
    private String name;
    private int price;
    private String picture;

    public ProductDto(Product product) {
        stock = product.getStock();
        name = product.getName();
        price = product.getPrice();
        picture = product.getPicture();
    }
}
