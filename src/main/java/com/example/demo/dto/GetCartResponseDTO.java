package com.example.demo.dto;

import com.example.demo.entity.Cart;
import lombok.Data;

@Data
public class GetCartResponseDTO {
    private Long id;
    private Long productId;
    private String name;
    private int price;
    private String picture;
    private int count;

    public GetCartResponseDTO(Cart cart) {
        id = cart.getId();
        productId = cart.getProduct().getId();
        name = cart.getProduct().getName();
        price = (int)(cart.getProduct().getPrice() * (1 - cart.getProduct().getDisc()));
        picture = cart.getProduct().getPicture();
        count = cart.getCount();

    }

}
