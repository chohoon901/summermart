package com.example.demo.dto;

import lombok.Data;

@Data
public class ProductSearchDTO {
    private Long id;
    private String name;
    private String picture;
    private int price;
    private double disc;
}
