package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentUpdateRequestDTO {
    private Long id;
    private String body;
}
