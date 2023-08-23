package com.example.demo.controller;

import com.example.demo.dto.ProductListDTO;
import com.example.demo.dto.ProductListCriteria;
import com.example.demo.service.ProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductListController {

    private final ProductListService productService;

    @Autowired
    public ProductListController(ProductListService productListService) {
        this.productService = productListService;
    }

    @PostMapping("/product_list")
    public List<ProductListDTO> getProducts(@RequestBody ProductListCriteria criteria) {
        return productService.getProductsByCriteria(
                criteria.getMainName(),
                criteria.getSubName(),
                criteria.getMinPrice(),
                criteria.getMaxPrice(),
                PageRequest.of(criteria.getPage(), criteria.getSize())
        );
    }
}
