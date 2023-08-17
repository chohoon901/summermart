package com.example.demo.service;

import com.example.demo.dto.MainDisplayDTO;
import com.example.demo.dto.ProductListDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductListRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductListService {

    private final ProductListRepository productListRepository;

    @Autowired
    public ProductListService(ProductListRepository productListRepository) {
        this.productListRepository = productListRepository;
    }

    public List<ProductListDTO> getProductList() {
        return productListRepository.findAll().stream()
                .map(product -> {
                    ProductListDTO dto = new ProductListDTO();
                    dto.setPicture(product.getPicture());
                    dto.setName(product.getName());
                    dto.setPrice(product.getPrice());
                    dto.setDisc(product.getDisc());
                    return dto;
                })
                .collect(Collectors.toList());

        // pagenation

//    public List<MainDisplayDTO> getTopProducts(int count) {
//        List<Product> products = mainDisplayRepository.findAll();
//        return products.stream()
//                .limit(count)  // 최대 열 개 상품만 반환
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }

    }


}
