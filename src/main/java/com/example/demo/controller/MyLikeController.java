package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.Member;
import com.example.demo.service.MyLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyLikeController {

    private final MyLikeService myLikeService;

    // @AuthenticationPrincipal
    // @RequestHeader 제거
    @PostMapping("/create_mylike/{productId}")
    public void createMyLike(@PathVariable Long productId,
                             @RequestBody CreateMyLikeMemberIdDTO createMyLikeMemberIdDTO) {
        myLikeService.createMyLikeById(productId, createMyLikeMemberIdDTO);
    }

    // 상품 화면에서 찜 제거
    @DeleteMapping("/delete_detail_mylike/{productId}")
    public void deleteMyLike(@PathVariable Long productId,
                             @RequestParam("memberId") Long memberId) {
        myLikeService.deleteMyLikeById(productId, memberId);
    }

    @DeleteMapping("/delete_mylike")
    public void deleteMyLike(@RequestParam("id") Long id) {
        myLikeService.deleteMyLike(id);
    }

    @PostMapping("/post_mylike_to_cart")
    public void postCart(@RequestBody MyLiketoCartRequestDTO myLiketoCartRequestDTO) {
//        System.out.println("myliek id =" + mylikeid);
        myLikeService.postCart(myLiketoCartRequestDTO);
//        return "cart add ok!!";
    }

    // @AuthenticationPrincipal
    @GetMapping("/select_mylike")
    public List<GetMyLikeResponseDTO> getAllLikes(@RequestParam("memberId") Long memberId) {
        return myLikeService.getAllLikes(memberId);
    }


    // 찜 화면에서 제거
}
