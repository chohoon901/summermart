package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartUpdateDTO {
    private Long id;
    private int updown; // true면 증가, false면 감소
}
