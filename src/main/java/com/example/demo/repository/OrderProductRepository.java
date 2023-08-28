package com.example.demo.repository;

import com.example.demo.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderProductRepository extends JpaRepository <OrderProduct, Long> {
    /**
     * 주어진 주문 상품 ID로 주문 상품을 찾습니다.
     *
     * @param orderItemId 주문 상품 ID
     * @return 주문 상품 (있을 경우), 없으면 빈 Optional 객체
     */
    Optional<OrderProduct> findById(Long orderItemId);

//    List<OrderProduct> findByOrderItemId(Long orderItemId);

    @Query("SELECT op FROM OrderProduct op JOIN op.orders o JOIN o.member m WHERE m.id = :memberId")
    List<OrderProduct> findByMemberId(Long memberId);

}
