package com.example.demo.dto;

import com.example.demo.entity.MyLike;
import com.example.demo.entity.Product;
import lombok.Data;

@Data
public class CreateMyLikeResponseDTO {
    private String name;
    private String picture;
    private int price;
    private double disc;

//    public CreateMyLikeResponseDTO() {
//    }
//
//    public CreateMyLikeResponseDTO(MyLike myLike) {
//        this.name = myLike.getProduct().getName();
//        this.picture = myLike.getProduct().getPicture();
//        this.price = myLike.getProduct().getPrice();
//        this.disc = myLike.getProduct().getDisc();
//    }
}
