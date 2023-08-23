package com.example.demo.controller;

import com.example.demo.dto.GetMyLikeResponseDTO;
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

    @PostMapping("/post_mylike_to_cart/{mylikeid}")
    public void postCart(@PathVariable Long mylikeid) {
//        System.out.println("myliek id =" + mylikeid);
        myLikeService.postCart(mylikeid);
//        return "cart add ok!!";
    }


    // @AuthenticationPrincipal
    @GetMapping("/select_mylike")
    public List<GetMyLikeResponseDTO> getAllLikes() {
        return myLikeService.getAllLikes();
    }

    @DeleteMapping("/delete_mylike/{mylikeid}")
    public void deleteMyLike(@PathVariable Long mylikeid) {
        myLikeService.deleteMyLike(mylikeid);
    }

    // detail에서 mylike delete
//    @DeleteMapping("/delete_mylike")
//    public void deleteMyLike(@RequestBody DTO DTO) {
//        myLikeService.deleteMyLike(DTO);
//    }

}
