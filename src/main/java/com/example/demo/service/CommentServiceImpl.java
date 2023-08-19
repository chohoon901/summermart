package com.example.demo.service;

import com.example.demo.dto.CommentResponseDTO;
import com.example.demo.dto.CreateCommentRequestDTO;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Member;
import com.example.demo.entity.Product;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CommentServiceImpl(
            CommentRepository commentRepository,
            MemberRepository memberRepository,
            ProductRepository productRepository
    ) {
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public CommentResponseDTO createComment(CreateCommentRequestDTO request) {
        Member member = memberRepository.findByUsername(request.getMemberName());
        Product product = productRepository.findByName(request.getProductName());

        if (member == null || product == null) {
            // 예외 처리
        }

        Comment comment = Comment.createComment(product, request.getBody(), LocalDateTime.now());
        comment.setMember(member);
        commentRepository.save(comment);

        return convertToCommentResponseDTO(comment);
    }

    @Override
    public Page<CommentResponseDTO> getCommentsByProductId(Long productId, Pageable pageable) {
        Page<Comment> comments = commentRepository.findByProductId(productId, pageable);
        return comments.map(this::convertToCommentResponseDTO);
    }

    private CommentResponseDTO convertToCommentResponseDTO(Comment comment) {
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setBody(comment.getBody());
        dto.setDate(comment.getDate());
        dto.setMemberName(comment.getMember().getName());
        dto.setProductName(comment.getProduct().getName());
        return dto;
    }
}
