package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MyLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public void setMember(Member member){
        if(this.member!=null) {
            this.member.getMyLikes().remove(this);
        }
        this.member = member;
        member.getMyLikes().add(this);
    }

    public void setProduct(Product product){
        if(this.product!=null) {
            this.product.getMyLikes().remove(this);
        }
        this.product = product;
        product.getMyLikes().add(this);
    }

    public static MyLike createMyLike(Product product) {
        MyLike myLike = new MyLike();
        myLike.setProduct(product);

        return myLike;
    }

}
