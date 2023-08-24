package com.example.demo.controller;

import com.example.demo.dto.CartDeleteRequestDTO;
import com.example.demo.dto.CartRequestDTO;
import com.example.demo.dto.CartUpdateDTO;
import com.example.demo.dto.GetCartResponseDTO;
import com.example.demo.entity.Cart;
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

    @GetMapping("/select_cart/{memberid}")
    public List<GetCartResponseDTO> getAllCarts(@PathVariable Long memberid,
                                                @AuthenticationPrincipal Member member,
                                                @RequestHeader("Authorization") String authorizationHeader) {
        return cartService.getAllCarts(memberid, member);
    }

    // Controller
    @DeleteMapping("/delete_cart")
    public void deleteCart(@RequestBody CartDeleteRequestDTO cartDeleteRequestDTO) {
        cartService.deleteCart(cartDeleteRequestDTO);
    }

    @PatchMapping("/update_cart")
    public void update (@RequestBody CartUpdateDTO cartUpdateDTO) {
        cartService.UpdateCart(cartUpdateDTO);
    }
}
