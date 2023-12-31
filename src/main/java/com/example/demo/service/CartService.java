package com.example.demo.service;

import com.example.demo.dto.CartRequestDTO;
import com.example.demo.dto.GetCartResponseDTO;
import com.example.demo.dto.*;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Member;
import com.example.demo.entity.Member;
import com.example.demo.entity.MyLike;
import com.example.demo.entity.Product;
import com.example.demo.exception.DuplicateProductException;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MyLikeRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
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

        boolean productExistsInCart = cartRepository.existsByProductId(productId);

        if (productExistsInCart) {
            throw new DuplicateProductException("해당 상품은 카트에 이미 있습니다.");
        }

        Cart cart = new Cart();
        cart.setMember(memberRepository.findById(cartRequestDTO.getMemberId()).orElseThrow());
        cart.setProduct(productRepository.findById(productId).orElseThrow());
        cart.setCount(cartRequestDTO.getCount());
        cartRepository.save(cart);
    }

    public List<GetCartResponseDTO> getAllCarts(Long memberId) {
        return cartRepository.findByMemberId(memberId)
                .stream()
                .map(GetCartResponseDTO::new)
                .collect(Collectors.toList());
    }

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

    public void deleteCart(Long cartid) {
        cartRepository.deleteById(cartid);
    }
}
