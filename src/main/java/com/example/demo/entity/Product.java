package com.example.demo.entity;

import com.example.demo.exception.NotEnoughStockException;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<MyLike> MyLikes = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "subCategory_id")
    private SubCategory subCategory;

    @Nullable
    private int stock;
    private String name;
    private int price;
    private String picture;
    private double disc;

    @Nullable
    private int commentCount;


    public void setSubCategory(SubCategory subCategory){
        if(this.subCategory!=null) {
            this.subCategory.getProduct().remove(this);
        }
        this.subCategory = subCategory;
        subCategory.getProduct().add(this);
    }

    public void addStock(int quantity) {
        this.stock += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stock - quantity;
        if(restStock < 0) {
            throw new NotEnoughStockException("재고 부족");
        }
        this.stock = restStock;
    }

    public void addComment () {
        this.commentCount++;
    }

    public static Product createProduct(SubCategory subCategory, int stock, String name, int price, String picture, double disc) {
        Product product = new Product();
        product.setSubCategory(subCategory);
        product.setStock(stock);
        product.setName(name);
        product.setPrice(price);
        product.setPicture(picture);
        product.setDisc(disc); // 할인률

        return product;
    }

}
