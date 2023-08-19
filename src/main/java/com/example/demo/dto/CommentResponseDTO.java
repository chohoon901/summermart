package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponseDTO {
    private String body;
    private LocalDateTime date;
    private String memberName;
    private String productName;
}