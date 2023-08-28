package com.example.demo.controller;

import com.example.demo.dto.OrderProductResponseDTO;
import com.example.demo.entity.Member;
import com.example.demo.service.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderProductController {

    private final OrderProductService orderProductService;

    // @AuthenticationPrincipal
    @GetMapping("/find_Orderproducts")
    public List<OrderProductResponseDTO> findOps(@AuthenticationPrincipal Member member) {
        return orderProductService.getOrderProducts(member);
    }

    @PatchMapping("/updateStatus")
    public void updateOrderProductStatus(@RequestBody RequestOrderStatusDTO requestDTO) {
        orderProductService.updateOrderStatus(requestDTO);
    }
}
