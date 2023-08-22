package com.example.demo.service;

import com.example.demo.dto.CartRequestDTO;
import com.example.demo.dto.GetCartResponseDTO;
import com.example.demo.entity.Cart;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;

    // id 1번인 member로 고정
    public void createCart(Long productId, CartRequestDTO cartRequestDTO) {
        Cart cart = new Cart();
        cart.setMember(memberRepository.findById(1L).orElseThrow());
        cart.setProduct(productRepository.findById(productId).orElseThrow());
        cart.setCount(cartRequestDTO.getCount());
        cartRepository.save(cart);

    }

    public List<GetCartResponseDTO> getAllCarts() {
        return cartRepository.findAllByMember_Id(1L)
                .stream()
                .map(GetCartResponseDTO::new)
                .collect(Collectors.toList());
    }

    public void deleteCart(Long cartid) {
        cartRepository.deleteById(cartid);
    }
}
