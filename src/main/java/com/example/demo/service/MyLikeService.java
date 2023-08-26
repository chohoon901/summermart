package com.example.demo.service;

import com.example.demo.dto.GetMyLikeResponseDTO;
import com.example.demo.entity.Member;
import com.example.demo.entity.MyLike;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MyLikeRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyLikeService {
    private final MyLikeRepository myLikeRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;


    public void createMyLike(Long productId, Member member) {
        Long id = memberRepository.findByUsername(member.getUsername()).getId();
        MyLike myLike = new MyLike();
        myLike.setProduct(productRepository.findById(productId).orElseThrow());
        myLike.setMember(memberRepository.findById(id).orElseThrow());
        myLikeRepository.save(myLike);

    }

    public List<GetMyLikeResponseDTO> getAllLikes(Member member) {
        Long id = memberRepository.findByUsername(member.getUsername()).getId();
        return myLikeRepository.findAllByMember_Id(id)
                .stream()
                .map(GetMyLikeResponseDTO::new)
                .collect(Collectors.toList());
    }

}
