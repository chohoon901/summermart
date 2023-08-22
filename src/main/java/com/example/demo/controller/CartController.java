package com.example.demo.controller;

import com.example.demo.dto.CartRequestDTO;
import com.example.demo.dto.GetCartResponseDTO;
import com.example.demo.service.CartService;
import lombok.RequiredArgsConstructor;
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
    public List<GetCartResponseDTO> getAllCarts() {
        return cartService.getAllCarts();
    }

    // Controller
    @DeleteMapping("/delete_cart/{cartid}")
    public void deleteCart(@PathVariable Long cartid) {
        cartService.deleteCart(cartid);
    }
}
