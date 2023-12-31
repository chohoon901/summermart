package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Member;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void createComment(CreateCommentRequestDTO createCommentRequestDTO, Long productId) {
        Long id = commentRepository.save(createCommentRequestDTO.toEntity()).getId();
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));

        System.out.println("createCommentRequestDTO.getMemberId() = " + createCommentRequestDTO.getMemberId());
        comment.setMember(memberRepository.findById(createCommentRequestDTO.getMemberId()).orElseThrow());
//        System.out.println("getUsername = " + member.getUsername());
        comment.setProduct(productRepository.findById(productId).orElseThrow());
        comment.getProduct().addComment();
        comment.setProduct(productRepository.findById(productId).orElseThrow());
    }

    public List<GetCommentResponseDTO> getComments(Long productId) {
        return commentRepository.findCommentsByProductId(productId)
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    private GetCommentResponseDTO entityToDto(Comment comment) {
        GetCommentResponseDTO getCommentResponseDTO = new GetCommentResponseDTO();
        getCommentResponseDTO.setId(comment.getId());
        getCommentResponseDTO.setBody(comment.getBody());
        //getCommentResponseDTO.setMemberName(member.getName());
        getCommentResponseDTO.setMemberName(comment.getMember().getName());
        return getCommentResponseDTO;
    }

    public void deleteComment(CommentDeleteRequestDTO commentDeleteRequestDTO) {
        Long commentid = commentDeleteRequestDTO.getId();
        commentRepository.deleteById(commentid);
    }

    public void updateComment(CommentUpdateRequestDTO commentUpdateRequestDTO) {
        Comment comment = commentRepository.findById(commentUpdateRequestDTO.getId())
                .orElseThrow(()->new IllegalArgumentException("해당 회원이 없습니다. id="+commentUpdateRequestDTO.getId()));

        if(commentUpdateRequestDTO.getBody()!=null){
            comment.setBody(commentUpdateRequestDTO.getBody());

            commentRepository.save(comment);
        }
    }

}
