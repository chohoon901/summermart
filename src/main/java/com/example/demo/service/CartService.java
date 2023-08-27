package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Member;
import com.example.demo.entity.MyLike;
import com.example.demo.entity.Product;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MyLikeRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        return cartRepository.findAllByMember_Id(3L)
                .stream()
                .map(GetCartResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Test
//    public Cart getCart() {
//        Cart cart = cartRepository.findByMemberIdAndProductId(3L,5L);
//        System.out.println("cart 값 = " + cart.getCount());
//        return cart;
//    }

    public void deleteCart(CartDeleteRequestDTO cartDeleteRequestDTO) {
        Long cartid = cartDeleteRequestDTO.getId();
        cartRepository.deleteById(cartid);
    }

    public void UpdateCart(CartUpdateDTO cartUpdateDTO){
        Cart cart = cartRepository.findById(cartUpdateDTO.getId())
                .orElseThrow(()->new IllegalArgumentException("해당 회원이 없습니다. id=" + cartUpdateDTO.getId()));

//        int countToUpdate = cartUpdateDTO.getCount();
//        if (countToUpdate != 0) {
//            cart.setCount(countToUpdate);
//        }

        if (cartUpdateDTO.getUpdown() == 1) {
            cart.addCount();
        } else {
            cart.deleteCount();
        }
//        } else if (cartUpdateDTO.getUpdown() == -1) {
//            cart.deleteCount();
//        } else {
//            exception
//        }

        cartRepository.save(cart);
    }
}
