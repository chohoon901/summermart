package com.example.demo.service;

import com.example.demo.dto.GetMemberResponseDTO;
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


    public void createMyLike(Long productId) {
        MyLike myLike = new MyLike();
        myLike.setProduct(productRepository.findById(productId).orElseThrow());
        myLike.setMember(memberRepository.findById(1L).orElseThrow());
        myLikeRepository.save(myLike);

    }

    public List<GetMyLikeResponseDTO> getAllLikes() {
        return myLikeRepository.findAllByMember_Id(1L)
                .stream()
                .map(GetMyLikeResponseDTO::new)
                .collect(Collectors.toList());
    }

    public void deleteMyLike(Long myLikeid) {

        myLikeRepository.deleteById(myLikeid);
    }

}
