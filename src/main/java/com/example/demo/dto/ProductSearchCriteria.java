package com.example.demo.dto;

import lombok.Data;

@Data
public class ProductSearchCriteria {
    private String mainName;
    private String subName;
    private int minPrice;
    private int maxPrice;
    private int page = 0;
    private int size = 10;
}