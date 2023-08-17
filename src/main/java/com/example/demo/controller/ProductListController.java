package com.example.demo.controller;

import com.example.demo.dto.ProductListDTO;
import com.example.demo.service.ProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product-list")
public class ProductListController {

    private final ProductListService productListService;

    @Autowired
    public ProductListController(ProductListService productListService) {
        this.productListService = productListService;
    }

    @GetMapping
    public List<ProductListDTO> getProductList() {
        return productListService.getProductList();

        //    @GetMapping("/display")
//    public List<MainDisplayDTO> getTopProducts() {
//        int numberOfProductsToDisplay = 10; // 표시할 상품 수
//        return mainDisplayService.getTopProducts(numberOfProductsToDisplay);
//    }

    }
}










