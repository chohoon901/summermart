package com.example.demo.controller;

import com.example.demo.dto.CreateCommentRequestDTO;
import com.example.demo.entity.Member;
import com.example.demo.service.MyLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MyLikeController {

    private final MyLikeService myLikeService;

    @PostMapping("/create_mylike/{productId}")
    public void createMylike(@PathVariable Long productId,
                              @AuthenticationPrincipal Member member,
                              @RequestHeader("Authorization") String authorizationHeader) {
        myLikeService.createMylike(productId, member);
    }
}
