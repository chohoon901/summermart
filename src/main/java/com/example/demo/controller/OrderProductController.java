package com.example.demo.controller;

import com.example.demo.dto.OrderProductRequestDTO;
import com.example.demo.service.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderProductController {

    private final OrderProductService orderProductService;

    // @AuthenticationPrincipal
    @GetMapping("/find_orderproducts")
    public List<OrderProductRequestDTO> findOps() {
        return orderProductService.getOrderProducts();
    }
}
