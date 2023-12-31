package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.Member;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create_comment/{productId}")
    public void createComment(@RequestBody CreateCommentRequestDTO createCommentRequestDTO,
                              @PathVariable Long productId) {
        commentService.createComment(createCommentRequestDTO, productId);
    }

    @GetMapping("/show_comments/{productId}")
    public List<GetCommentResponseDTO> showComment(@PathVariable Long productId) {
        return commentService.getComments(productId);
    }

    @DeleteMapping("/delete_comment")
    public void deleteMyLike(@RequestBody CommentDeleteRequestDTO commentDeleteRequestDTO) {
        commentService.deleteComment(commentDeleteRequestDTO);
    }

    @PatchMapping("/update_comment")
    public void updateComment(@RequestBody CommentUpdateRequestDTO commentUpdateRequestDTO) {
//        System.out.println("memberUpdateRequestDTO = " + memberUpdateRequestDTO);
//        return null;
        commentService.updateComment(commentUpdateRequestDTO);
    }
}
