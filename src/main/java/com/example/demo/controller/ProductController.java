package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    @PostMapping("/create_product")
    public void createProduct(@RequestBody Product product) {
        productRepository.save(product);
    }

    @PostMapping("/post_productDTO")
    public void createProductDto(@RequestBody ProductDTO productDTO) {
        productService.createProduct(productDTO);
    }

//    @DeleteMapping
//
//    @PutMapping
//
//    @PatchMapping

    @GetMapping("/get_product")
    public List<ProductDTO> getProduct() {
        return productService.getAllProducts();
    }


}
