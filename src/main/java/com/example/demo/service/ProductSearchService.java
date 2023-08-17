package com.example.demo.service;

import com.example.demo.dto.ProductSearchDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSearchService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductSearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductSearchDTO> searchProducts(String keyword) {
        List<Product> products = productRepository.findByNameContaining(keyword);
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProductSearchDTO convertToDTO(Product product) {
        ProductSearchDTO dto = new ProductSearchDTO();
        dto.setName(product.getName());
        dto.setPicture(product.getPicture());
        dto.setPrice(product.getPrice());
        dto.setDisc(product.getDisc());
        return dto;
    }
}