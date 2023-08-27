package com.example.demo.dto;

import com.example.demo.entity.OrderStatus;
import lombok.Data;

@Data
public class RequestOrderStatusDTO {
    private Long orderItemId;
}

//    private OrderStatus orderStatus;
