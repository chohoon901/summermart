package com.example.demo.repository;

import com.example.demo.entity.MyLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyLikeRepository extends JpaRepository<MyLike, Long> {
    Integer findById(int id);
}
