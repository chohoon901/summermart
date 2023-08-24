package com.example.demo.repository;

import com.example.demo.entity.Cart;
import com.example.demo.entity.MyLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository <Cart, Long> {

    List<Cart> findAllByMember_Id(Long memberid);

    // Repository
    void deleteById(Long id);
}
