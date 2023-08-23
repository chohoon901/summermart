package com.example.demo.controller;

import com.example.demo.dto.CommentUpdateRequestDTO;
import com.example.demo.dto.CreateCommentRequestDTO;
import com.example.demo.dto.GetCommentResponseDTO;
import com.example.demo.dto.MemberUpdateRequestDTO;
import com.example.demo.entity.Member;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create_comment/{productId}")
    public void createComment(@RequestBody CreateCommentRequestDTO createCommentRequestDTO,
                              @PathVariable Long productId,
                              @AuthenticationPrincipal Member member,
                              @RequestHeader("Authorization") String authorizationHeader) {
        commentService.createComment(createCommentRequestDTO, productId, member);
    }

    @GetMapping("/select_comment/{productId}")
    public List<GetCommentResponseDTO> showComment(@PathVariable Long productId,
                                                   @RequestHeader("Authorization") String authorizationHeader) {
        return commentService.getComments(productId);
    }

    @DeleteMapping("/delete_comment/{commentid}")
    public void deleteMyLike(@PathVariable Long commentid) {
        commentService.deleteComment(commentid);
    }

    @PatchMapping("/update_comment")
    public void updateComment(@RequestBody CommentUpdateRequestDTO commentUpdateRequestDTO) {
//        System.out.println("memberUpdateRequestDTO = " + memberUpdateRequestDTO);
//        return null;
        commentService.updateComment(commentUpdateRequestDTO);
    }
}
