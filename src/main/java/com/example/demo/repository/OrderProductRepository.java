package com.example.demo.repository;

import com.example.demo.entity.MyLike;
import com.example.demo.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderProductRepository extends JpaRepository <OrderProduct, Long> {
    @Query("SELECT op FROM OrderProduct op JOIN FETCH op.order o JOIN FETCH o.member m WHERE m.username = :username")
    List<OrderProduct> findByMemberUsername(String username);
}
