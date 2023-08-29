package com.example.demo.dto;

import com.example.demo.entity.Product;
import lombok.Data;

@Data
public class GetProductResponseDTO {
    private String name;
    private int stock;
    private int price;
    private String picture;
    private String subName;
    private String mainName;
    private double disc;
    private int commentCount;
    private boolean isLiked;

    public GetProductResponseDTO(Product product, boolean isLiked1) {
        mainName = product.getSubCategory().getMainCategory().getMainName();
        subName = product.getSubCategory().getSubName();
        picture = product.getPicture();
        name = product.getName();
        stock = product.getStock();
        price = product.getPrice();
        disc = product.getDisc();
        commentCount = product.getCommentCount();
        isLiked = isLiked1;
    }
}
