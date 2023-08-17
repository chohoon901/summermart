package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainDisplayRepository extends JpaRepository<Product, Long> {
}