package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "orders")
    private List<OrderProduct> orderProducts = new ArrayList<>();


    private int count;
//    private LocalDateTime date;

    public void setMember(Member member){
        if(this.member!=null) {
            this.member.getOrders().remove(this);
        }
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderProduct orderProduct){
        orderProducts.add(orderProduct);
        orderProduct.setOrders(this);
    }


    public static Orders createOrder(Member member, OrderProduct... orderProducts) {
        Orders orders = new Orders();
        orders.setMember(member);
        for(OrderProduct orderProduct : orderProducts){
            orders.addOrderItem(orderProduct);
        }

//        orders.setDeliveryStatus(DeliveryStatus.READY);
//        orders.setDate(LocalDateTime.now());
        return orders;
    }

}

