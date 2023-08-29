package com.example.demo.dto;

import lombok.Data;

@Data
public class CreateOrderRequestDTO {
    private Long memberId;
    private String productIds;
}
