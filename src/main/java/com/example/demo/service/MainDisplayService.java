package com.example.demo.service;

import com.example.demo.dto.MainDisplayDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.MainDisplayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainDisplayService {
    private final MainDisplayRepository mainDisplayRepository;
    @Autowired
    public MainDisplayService(MainDisplayRepository mainDisplayRepository) {
        this.mainDisplayRepository = mainDisplayRepository;
    }
    public List<MainDisplayDTO> getTopProducts(int count) {
        List<Product> products = mainDisplayRepository.findAll();
        return products.stream()
                .limit(count)  // 최대 열 개 상품만 반환
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private MainDisplayDTO convertToDTO(Product product) {
        MainDisplayDTO dto = new MainDisplayDTO();
        dto.setId(product.getId()); //<-- 수정 아이디 누락
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setPicture(product.getPicture());
        return dto;
    }
    public MainDisplayDTO getProductById(Long id) {
        Product product = mainDisplayRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
        if (product!=null) {
            return convertToDTO(product);
        }else {
            return null;
        }
    }
}
