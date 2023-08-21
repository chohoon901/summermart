package com.example.demo.service;

import com.example.demo.dto.CreateCommentRequestDTO;
import com.example.demo.dto.CreateMyLikeResponseDTO;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Member;
import com.example.demo.entity.MyLike;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MyLikeRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyLikeService {

    private final MyLikeRepository myLikeRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;


    public void createMylike(Long productId, Member member) {
        MyLike myLike = new MyLike();
        myLike.setProduct(productRepository.findById(productId).orElseThrow());
        myLike.setMember(memberRepository.findByUsername(member.getUsername()));
        myLikeRepository.save(myLike);
    }


}
