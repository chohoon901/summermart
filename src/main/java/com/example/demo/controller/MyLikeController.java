package com.example.demo.controller;

import com.example.demo.dto.GetMyLikeResponseDTO;
import com.example.demo.dto.MyLikeDeleteRequestDTO;
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
                             @AuthenticationPrincipal Member member) {
        myLikeService.createMyLikeById(productId, member);
    }

    // 상품 화면에서 찜 제거
    @DeleteMapping("/delete_mylike/{productId}")
    public void deleteMyLike(@PathVariable Long productId,
                             @AuthenticationPrincipal Member member) {
        myLikeService.deleteMyLikeById(productId, member);
    }

    @PostMapping("/post_mylike_to_cart/{mylikeid}")
    public void postCart(@PathVariable Long mylikeid) {
//        System.out.println("myliek id =" + mylikeid);
        myLikeService.postCart(mylikeid);
//        return "cart add ok!!";
    }

    // @AuthenticationPrincipal
    @GetMapping("/select_mylike")
    public List<GetMyLikeResponseDTO> getAllLikes(@AuthenticationPrincipal Member member) {
        return myLikeService.getAllLikes(member);
    }


    // 찜 화면에서 제거
    @DeleteMapping("/delete_mylike")
    public void deleteMyLike(@RequestBody MyLikeDeleteRequestDTO myLikeDeleteRequestDTO) {
        myLikeService.deleteMyLike(myLikeDeleteRequestDTO);
    }
}
