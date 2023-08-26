package com.example.demo.controller;

import com.example.demo.dto.CartRequestDTO;
import com.example.demo.dto.GetCartResponseDTO;
import com.example.demo.entity.Member;
import com.example.demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/create_cart/{productId}")
    public void createCart(@PathVariable Long productId, @RequestBody CartRequestDTO cartRequestDTO) {
        cartService.createCart(productId, cartRequestDTO);
    }

    @GetMapping("/select_cart")
    public List<GetCartResponseDTO> getAllCarts(@AuthenticationPrincipal Member member) {
        return cartService.getAllCarts(member);
    }

    @DeleteMapping("/delete_cart")
    public void deleteCart(@RequestParam("id") Long cartid) {
        cartService.deleteCart(cartid);
    }
}
