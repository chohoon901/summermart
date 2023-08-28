package com.example.demo.controller;

import com.example.demo.dto.ProductSearchDTO;
import com.example.demo.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductSearchController {

    private final ProductSearchService productSearchService;

//    @GetMapping("/product_search")
//    public List<ProductSearchDTO> searchProducts(@RequestBody Map<String, String> keyword) {
//        return productSearchService.searchProducts(keyword.get("keyword"));
//    }

    @GetMapping("/product_search")
    public List<ProductSearchDTO> searchProducts(@RequestParam String keyword) {
        return productSearchService.searchProducts(keyword);
    }
}
