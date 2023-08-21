package com.example.demo.controller;

import com.example.demo.dto.ProductSearchDTO;
import com.example.demo.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ProductSearchController {
    private final ProductSearchService productSearchService;

    @Autowired
    public ProductSearchController(ProductSearchService productSearchService) {
        this.productSearchService = productSearchService;
    }

    @GetMapping("/product_search")
    public List<ProductSearchDTO> searchProducts(@RequestBody Map<String, String> keyword) {
        return productSearchService.searchProducts(keyword.get("keyword"));
    }
}
