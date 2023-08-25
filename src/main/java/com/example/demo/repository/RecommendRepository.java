package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendRepository extends JpaRepository<Product, String> {

    @Query(value = "SELECT id, name FROM product WHERE name LIKE '%{word}%'", nativeQuery = true)
    List<Product> findProd(@Param(value="word") String word);

}
