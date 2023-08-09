package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MyLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public static MyLike createMyLike(Product product) {
        MyLike myLike = new MyLike();
        myLike.setProduct(product);

        return myLike;
    }

}
