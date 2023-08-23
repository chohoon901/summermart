package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "orders")
    private List<OrderProduct> orderProducts = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

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
        orders.setOrderStatus(OrderStatus.ORDER);
        orders.setDeliveryStatus(DeliveryStatus.READY);
//        orders.setDate(LocalDateTime.now());
        return orders;
    }

}

