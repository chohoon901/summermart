package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private String body;
    private LocalDateTime date;

    public void setMember(Member member){
        if(this.member!=null) {
            this.member.getComments().remove(this);
        }
        this.member = member;
        member.getComments().add(this);
    }

    public void setProduct(Product product){
        if(this.product!=null) {
            this.product.getComments().remove(this);
        }
        this.product = product;
        product.getComments().add(this);
    }

    public static Comment createComment(Product product, String body, LocalDateTime date) {
        Comment comment = new Comment();
        comment.setProduct(product);
        comment.setBody(body);
        comment.setDate(date);

        return comment;
    }
}
