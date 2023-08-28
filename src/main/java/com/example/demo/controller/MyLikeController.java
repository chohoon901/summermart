package com.example.demo.controller;

import com.example.demo.dto.GetMyLikeResponseDTO;
import com.example.demo.dto.MyLikeDeleteRequestDTO;
import com.example.demo.dto.MyLiketoCartRequestDTO;
import com.example.demo.entity.Member;
import com.example.demo.entity.MyLike;
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
    public void createMyLike(@PathVariable Long productId) {
        myLikeService.createMyLike(productId);
    }

    // @AuthenticationPrincipal
    @GetMapping("/select_mylike")
    public List<GetMyLikeResponseDTO> getAllLikes() {
        return myLikeService.getAllLikes();
    }

//    @DeleteMapping("/delete_detail_mylike/{mylikeid}")
//    public void deleteDetailsMyLike(@PathVariable Long mylikeid) {
//        myLikeService.deleteMyLike(mylikeid);
//    }

    @DeleteMapping("/delete_detail_mylike/{productId}")
    public void deleteMyLike(@PathVariable Long productId,
                             @AuthenticationPrincipal Member member) {
        myLikeService.deleteMyLikeById(productId, member);
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

}
