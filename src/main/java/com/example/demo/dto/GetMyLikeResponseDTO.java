package com.example.demo.dto;

import com.example.demo.entity.MyLike;
import com.example.demo.entity.Product;
import lombok.Data;

@Data
public class GetMyLikeResponseDTO {
    private String name;
    private int price;
    private String picture;
    private double disc;

    public GetMyLikeResponseDTO(MyLike myLike) {
        name = myLike.getProduct().getName();
        price = myLike.getProduct().getPrice();
        picture = myLike.getProduct().getPicture();
        disc = myLike.getProduct().getDisc();
    }
}
