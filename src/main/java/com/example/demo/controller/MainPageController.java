package com.example.demo.controller;

import com.example.demo.dto.MainPageDTO;
import com.example.demo.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MainPageController {

    private MainPageService mainPageService;

    @GetMapping("/show")
    public List<MainPageDTO> getTopProducts() {
        int numberOfProductsToDisplay = 10; // 표시할 상품 수
        return mainPageService.getTopProducts(numberOfProductsToDisplay);
    }
    @GetMapping("/show_product")
    public MainPageDTO getProductById(@RequestBody Map<String, Long> request) {
        Long id = request.get("id");
//        System.out.println("Received id: " + id); // Add this line for debugging
        return mainPageService.getProductById(id);
    }
}
