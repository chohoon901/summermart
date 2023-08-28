package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query("select p from Product p left join fetch p.SubCategory")
//    List<Product> findProductFetchJoin();
//    List<Product> findByIdIn(List<Long> productIds);

    List<Product> findByIdIn(List<Long> productIds);

    // name을 포함하는 단어를 전부 찾아서 반환
    List<Product> findByNameContaining(String name);

    Product findByName(String name);
}
