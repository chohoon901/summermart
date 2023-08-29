package com.example.demo.dto;

import lombok.Data;

@Data
public class GetComparePasswordDTO {
    private Long memberId;
    private String password;
}
