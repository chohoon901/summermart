package com.example.demo.repository;

import com.example.demo.entity.Member;
import com.example.demo.entity.MyLike;
import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MyLikeRepository extends JpaRepository <MyLike, Long> {
//    Integer findById(int id);
    List<MyLike> findAllByMember_Id(Long memberId);
    void deleteById(Long id);

    @Transactional
    void deleteByMemberIdAndProductId(Long memberId, Long productId);

    boolean existsByMemberAndProduct(Member member, Product product);
}
