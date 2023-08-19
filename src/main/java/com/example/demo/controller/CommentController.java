package com.example.demo.controller;

import com.example.demo.dto.CommentResponseDTO;
import com.example.demo.dto.CreateCommentRequestDTO;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create_comments")
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CreateCommentRequestDTO request) {
        CommentResponseDTO createdComment = commentService.createComment(request);
        return ResponseEntity.ok(createdComment);
    }

    @GetMapping("/create_comment/product/{productId}")
    public ResponseEntity<Page<CommentResponseDTO>> getCommentsByProductId(
            @PathVariable Long productId,
            @RequestBody Pageable pageable
    ) {
        Page<CommentResponseDTO> comments = commentService.getCommentsByProductId(productId, pageable);
        return ResponseEntity.ok(comments);
    }
}
