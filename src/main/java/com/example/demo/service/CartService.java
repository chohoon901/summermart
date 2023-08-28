package com.example.demo.service;

import com.example.demo.dto.CartDeleteRequestDTO;
import com.example.demo.dto.CartRequestDTO;
import com.example.demo.dto.CartUpdateDTO;
import com.example.demo.dto.GetCartResponseDTO;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Member;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
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

    public List<GetCartResponseDTO> getAllCarts(Member member) {
        return cartRepository.findByMemberUsername(member.getUsername())
                .stream()
                .map(GetCartResponseDTO::new)
                .collect(Collectors.toList());
    }
    public void deleteCart(Long cartid) {
        cartRepository.deleteById(cartid);
    }

    public void deleteCart(CartDeleteRequestDTO cartDeleteRequestDTO) {
        Long cartid = cartDeleteRequestDTO.getId();
        cartRepository.deleteById(cartid);
    }

    public void UpdateCart(CartUpdateDTO cartUpdateDTO){
        Cart cart = cartRepository.findById(cartUpdateDTO.getId())
                .orElseThrow(()->new IllegalArgumentException("해당 회원이 없습니다. id=" + cartUpdateDTO.getId()));
        if (cartUpdateDTO.getUpdown() == 1) {
            cart.addCount();
        } else {
            cart.deleteCount();
        }
        cartRepository.save(cart);
    }

}
