package com.example.demo.dto;

import lombok.Data;

@Data
public class GetCommentResponseDTO {
    private Long id;
    private String body;
    private String memberName;
}
