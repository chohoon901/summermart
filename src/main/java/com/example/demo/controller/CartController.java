package com.example.demo.controller;

import com.example.demo.dto.CartDeleteRequestDTO;
import com.example.demo.dto.CartRequestDTO;
import com.example.demo.dto.CartUpdateDTO;
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

//    @DeleteMapping("/delete_cart/{cartid}")
//    public void deleteCart(@PathVariable Long cartid) {
//        cartService.deleteCart(cartid);
//    }

    @DeleteMapping("/delete_cart")
    public void deleteCart(@RequestBody CartDeleteRequestDTO cartDeleteRequestDTO) {
        cartService.deleteCart(cartDeleteRequestDTO);
    }

    @PatchMapping("/update_cart")
    public void update (@RequestBody CartUpdateDTO cartUpdateDTO) {
        cartService.UpdateCart(cartUpdateDTO);
    }
}
