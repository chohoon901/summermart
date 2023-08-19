package com.example.demo.service;

import com.example.demo.dto.CommentResponseDTO;
import com.example.demo.dto.CreateCommentRequestDTO;
import com.example.demo.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    CommentResponseDTO createComment(CreateCommentRequestDTO request);
    Page<CommentResponseDTO> getCommentsByProductId(Long productId, Pageable pageable);
}
