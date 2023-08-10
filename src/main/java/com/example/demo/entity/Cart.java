package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int count;

    public void setMember(Member member){
        if(this.member!=null) {
            this.member.getCarts().remove(this);
        }
        this.member = member;
        member.getCarts().add(this);
    }

    public void setProduct(Product product){
        if(this.product!=null) {
            this.product.getCarts().remove(this);
        }
        this.product = product;
        product.getCarts().add(this);
    }

    public static Cart createCart(Product product, int count) {
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setCount(count);

        return cart;
    }
}
