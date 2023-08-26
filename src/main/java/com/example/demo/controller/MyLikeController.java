package com.example.demo.controller;

import com.example.demo.dto.GetMyLikeResponseDTO;
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
        myLikeService.createMyLike(productId, member);
    }

    // @AuthenticationPrincipal
    @GetMapping("/select_mylike")
    public List<GetMyLikeResponseDTO> getAllLikes(@AuthenticationPrincipal Member member) {
        return myLikeService.getAllLikes(member);
    }
}
