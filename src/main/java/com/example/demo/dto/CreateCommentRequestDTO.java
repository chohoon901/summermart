package com.example.demo.dto;

import lombok.Data;

@Data
public class CreateCommentRequestDTO {
    private String body;
    private String memberName;
    private String productName;
}