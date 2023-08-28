package com.example.demo.service;

import com.example.demo.dto.GetMyLikeResponseDTO;
import com.example.demo.entity.Member;
import com.example.demo.entity.MyLike;
import com.example.demo.dto.MyLikeDeleteRequestDTO;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Member;
import com.example.demo.entity.MyLike;
import com.example.demo.entity.Product;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MyLikeRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyLikeService {
    private final MyLikeRepository myLikeRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;


    // 성훈씨 작업물
    public void createMyLikeById(Long productId, Member member) {
        MyLike myLike = new MyLike();
        myLike.setProduct(productRepository.findById(productId).orElseThrow());
        myLike.setMember(memberRepository.findByUsername(member.getUsername()));
        myLikeRepository.save(myLike);
    }

    public void deleteMyLikeById(Long productId, Member member) {
        MyLike myLike1 = new MyLike();
        myLike1.setProduct(productRepository.findById(productId).orElseThrow());
        // member.getid()
        myLike1.setMember(memberRepository.findByUsername(member.getUsername()));
        myLikeRepository.deleteByMemberIdAndProductId(memberRepository.findByUsername(member.getUsername()).getId(), productId);
    }


    public void postCart (Long id) {
        Optional<MyLike> mylike = myLikeRepository.findById(id);
        MyLike myLikeOne = mylike.get();
        //System.out.println("mylikeOne 출력= " + myLikeOne);

        Cart cart1 = new Cart();
        cart1.setCount(1);
        Product product = myLikeOne.getProduct();
        Member member = myLikeOne.getMember();
        cart1.setProduct(product);
        cart1.setMember(member);
        cartRepository.save(cart1);
    }

    public List<GetMyLikeResponseDTO> getAllLikes(Member member) {
        Long id = memberRepository.findByUsername(member.getUsername()).getId();
        return myLikeRepository.findAllByMember_Id(id)
                .stream()
                .map(GetMyLikeResponseDTO::new)
                .collect(Collectors.toList());
    }

    public void deleteMyLike(MyLikeDeleteRequestDTO myLikeDeleteRequestDTO) {
        Long myLikeid = myLikeDeleteRequestDTO.getId();
        myLikeRepository.deleteById(myLikeid);
    }

}
