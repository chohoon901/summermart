package com.example.demo.dto;

import lombok.Data;

@Data
public class CreateMyLikeRequestDTO {
    private Long id;
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
