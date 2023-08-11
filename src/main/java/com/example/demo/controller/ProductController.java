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

    @PostMapping("/api")
    public void createProduct(@RequestBody Product product) {
        productRepository.save(product);
    }

    @PostMapping("/api2")
    public void createProductDto(@RequestBody ProductDTO productDTO) {
        productService.createProduct(productDTO);
    }

    @DeleteMapping

    // 전부 교체
    @PutMapping
    // 하나만 교체
    @PatchMapping

    @GetMapping("/api3")
    public List<ProductDTO> getProduct() {
        return productService.getAllProducts();
    }

//    private final ProductService productService;
//
//    @PostMapping("/api")
//    public void createProduct(@RequestBody Map<String, String> param) {
//        productService.createProduct(product.getSubCategory().getMainCategory().getMainName(),
//                product.getSubCategory().getSubName(), product.getStock(),
//                product.getName(), product.getPrice(), product.getPicture() );
//        StringBuilder sb = new StringBuilder();
//
//        param.entrySet().forEach(entry -> {
//        });
//
//    }
//
//    @GetMapping("/api2")
//    public List<ProductDTO> getProduct(@RequestBody Product product) {
//        return productService.getProduct();
//    }
//

//    // 상품 정보 생성을 위한 POST 요청 처리
//    @PostMapping
//    public Product creatProduct(@RequestBody ProductRequest request) {
//        return productService.createProduct(request.getstock(), request.Name(), request.Price(),
//                request.Picture());
//    }
//
//    // 모든 상품 정보 조회를 위한 GET 요청 처리
//    @GetMapping
//    public List<Product> getAllProduct() {
//        return productService.getAllProducts();
//    }
}
