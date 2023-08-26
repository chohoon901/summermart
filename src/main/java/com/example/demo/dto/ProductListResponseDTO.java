package com.example.demo.dto;

import lombok.Data;

@Data
public class ProductListResponseDTO {
    private Long id;
    private String picture;
    private String name;
    private int price;
    private double disc;

}
