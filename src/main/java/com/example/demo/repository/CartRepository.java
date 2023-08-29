package com.example.demo.repository;

import com.example.demo.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository <Cart, Long> {

//    Cart findByMemberIdAndProductId(Long memberId, Long productId);

    Cart findByMemberIdAndProductId(Long memberId, Long productId);


    List<Cart> findAllByMember_Id(Long memberId);

    @Query("SELECT c FROM Cart c JOIN FETCH c.member m WHERE m.username = :username")
    List<Cart> findByMemberUsername(String username);

    @Query("SELECT c FROM Cart c JOIN FETCH c.member m WHERE m.id = :memberId")
    List<Cart> findByMemberId(Long memberId);

    void deleteById(Long id);

    boolean existsByProductId(Long productId);
}
