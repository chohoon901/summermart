package com.example.demo.repository;

import com.example.demo.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository <Cart, Long> {

    List<Cart> findAllByMember_Id(Long memberId);

    void deleteById(Long id);
}
