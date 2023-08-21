package com.example.demo.repository;

import com.example.demo.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderProductRepository extends JpaRepository <OrderProduct, Long> {
    @Query("SELECT op FROM OrderProduct op JOIN op.orders o JOIN o.member m WHERE m.id = :memberId")
    List<OrderProduct> findByMember_Id(Long memberId);
}
