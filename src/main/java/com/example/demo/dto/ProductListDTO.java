package com.example.demo.dto;

import lombok.Data;

@Data
public class ProductListDTO {
    private Long id;
    private String picture;
    private String name;
    private int price;
    private double disc;
    private String subName;
    private String mainName;

}
