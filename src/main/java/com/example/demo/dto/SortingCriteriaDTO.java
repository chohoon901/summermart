package com.example.demo.dto;

import lombok.Data;

@Data
public class SortingCriteriaDTO {
    private int pageNumber;
    private String categoryName;
    private String sortBy;
    private String sortDirection;
}
