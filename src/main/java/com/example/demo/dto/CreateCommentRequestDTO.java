package com.example.demo.dto;

import com.example.demo.entity.Comment;
import lombok.Data;

@Data
public class CreateCommentRequestDTO {
    private String body;

    public Comment toEntity() {
        Comment comment = new Comment();
        comment.setBody(body);
        return comment;
    }
}
