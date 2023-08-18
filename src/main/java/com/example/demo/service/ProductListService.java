package com.example.demo.service;

import com.example.demo.dto.ProductListDTO;
import com.example.demo.util.CustomPageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductListService {
    List<ProductListDTO> getProductsByCriteria(String mainName, String subName, int minPrice, int maxPrice, Pageable pageable);
}


