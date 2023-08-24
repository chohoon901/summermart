package com.example.demo.repository;

import com.example.demo.entity.MyLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyLikeRepository extends JpaRepository <MyLike, Long> {
//    Integer findById(int id);
    List<MyLike> findAllByMember_Id(Long memberId);

    void deleteById(Long id);

    @Query("SELECT ml.id FROM MyLike ml WHERE ml.member.id = :memberId AND ml.product.id = :productId")
    Long findMyLikeIdByMemberIdAndProductId(Long memberId, Long productId);


}
