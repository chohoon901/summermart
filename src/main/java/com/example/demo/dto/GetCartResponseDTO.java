package com.example.demo.dto;

import com.example.demo.entity.Cart;
import lombok.Data;

@Data
public class GetCartResponseDTO {
    private Long id;
    private String name;
    private int price;
    private String picture;
    private int count;

    public GetCartResponseDTO(Cart cart) {
        id = cart.getId();
        name = cart.getProduct().getName();
        price = cart.getProduct().getPrice();
        picture = cart.getProduct().getPicture();
        count = cart.getCount();
    }
}
