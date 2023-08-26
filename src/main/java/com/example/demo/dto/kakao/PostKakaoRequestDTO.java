package com.example.demo.dto.kakao;

import lombok.Data;

@Data
public class PostKakaoRequestDTO {


    private String item_name;
    private int quantity;
    private int total_amount;
}
