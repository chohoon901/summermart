package com.example.demo.repository;

import com.example.demo.entity.MyLike;
import com.example.demo.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository <OrderProduct, Long> {
    List<OrderProduct> findAllByMember_Id(Long memberId);
}
