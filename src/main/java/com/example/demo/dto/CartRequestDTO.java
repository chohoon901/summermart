package com.example.demo.dto;

import lombok.Data;

@Data
public class CartRequestDTO {
    private Long memberId;
    private int count;
}
