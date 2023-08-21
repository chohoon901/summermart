package com.example.demo.dto;

import com.example.demo.entity.Comment;
import lombok.Data;

@Data
public class GetCommentResponseDTO {
    private String body;
    private String memberName;
}
