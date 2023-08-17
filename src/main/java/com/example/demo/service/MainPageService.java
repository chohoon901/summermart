package com.example.demo.service;

import com.example.demo.dto.MainPageDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.MainPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainPageService {

    private final MainPageRepository mainPageRepository;

    @Autowired
    public MainPageService(MainPageRepository mainPageRepository) {
        this.mainPageRepository = mainPageRepository;
    }

    public List<MainPageDTO> getTopProducts(int count) {
        List<Product> products = mainPageRepository.findAll();
        return products.stream()
                .limit(count)  // 최대 열 개 상품만 반환
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MainPageDTO convertToDTO(Product product) {
        MainPageDTO dto = new MainPageDTO();

        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setPicture(product.getPicture());
        return dto;
    }

    public MainPageDTO getProductById(Long id) {
        Product product = mainPageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        return convertToDTO(product);
    }
}
