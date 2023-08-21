package com.example.demo.dto;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.entity.Comment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateCommentRequestDTO {
    private String body;

    public CreateCommentRequestDTO() {
    }

    public CreateCommentRequestDTO(Comment comment) {
        body = comment.getBody();
    }

    public Comment toEntity() {
        Comment comment = new Comment();
        comment.setBody(body);
        return comment;
    }
}
