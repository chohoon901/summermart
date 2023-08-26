package com.example.demo.service;

import com.example.demo.dto.ProductListResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductListService {
    List<ProductListResponseDTO> getProductsByCriteria(String mainName, String subName, int minPrice, int maxPrice, Pageable pageable);
}


