package com.example.demo.dto;

import com.example.demo.entity.Cart;
import lombok.Data;

@Data
public class GetCartResponseDTO {
    private String name;
    private int price;
    private String picture;
    private int count;

    public GetCartResponseDTO(Cart cart) {
        name = cart.getProduct().getName();
        price = cart.getProduct().getPrice();
        picture = cart.getProduct().getPicture();
        count = cart.getCount();
    }
}