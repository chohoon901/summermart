package com.example.demo.repository;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Member;
import com.example.demo.entity.MyLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CartRepository extends JpaRepository <Cart, Long> {

    Cart findByMemberIdAndProductId(Long memberId, Long productId);

    // Product To Orderproduct
    @Query("SELECT c.count FROM Cart c WHERE c.member.id = :memberId AND c.product.id = :productId")
    Integer findCountByMemberIdAndProductId(@Param("memberId") Long memberId, @Param("productId") Long productId);


    List<Cart> findAllByMember_Id(Long memberId);

    // Repository
    void deleteById(Long id);
}
