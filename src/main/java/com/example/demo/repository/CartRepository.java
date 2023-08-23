package com.example.demo.repository;

import com.example.demo.entity.Cart;
import com.example.demo.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository <Cart, Long> {

    List<Cart> findAllByMember_Id(Long memberId);

    @Query("SELECT c FROM Cart c JOIN FETCH c.member m WHERE m.username = :username")
    List<Cart> findByMemberUsername(String username);

    void deleteById(Long id);
}
