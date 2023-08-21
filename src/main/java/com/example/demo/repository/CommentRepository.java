package com.example.demo.repository;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

//    @Query("SELECT c FROM Comment c WHERE c.product.id = :productId")
//    List<Comment> findByProductIdUsingJPQL(Long productId);

    @Query("SELECT c FROM Comment c JOIN FETCH c.member m WHERE c.product.id = :productId")
    List<Comment> findByProductIdWithMemberFetch(Long productId);
}
