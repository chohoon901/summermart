package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductListRepository extends JpaRepository<Product, Long> {

    // 대분류로 찾기
    Page<Product> findBySubCategory_MainCategory_MainName(String mainName, Pageable pageable);

    // 소분류로 찾기
    Page<Product> findBySubCategory_SubName(String subName, Pageable pageable);


    Page<Product> findAll(Specification<Product> specification, Pageable pageable);

//    // 가격 내림차순
//    List<Product> findAllByOrderByPriceDesc();
//    // 가격 오름차순
//    List<Product> findAllByOrderByPriceAsc();
//    // 할인률 오름차순
//    List<Product> findAllByOrderByDiscAsc();
}