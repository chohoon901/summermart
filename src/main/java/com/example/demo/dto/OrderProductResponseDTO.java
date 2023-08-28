package com.example.demo.dto;

import com.example.demo.entity.OrderProduct;
import com.example.demo.entity.OrderStatus;
import lombok.Data;

@Data
public class OrderProductResponseDTO {
    private Long id;
    private String name;
    private int price;
    private int count;
    private String picture;
    private OrderStatus orderStatus;

    public OrderProductResponseDTO(OrderProduct orderProduct) {
        id = orderProduct.getId();
        name = orderProduct.getProduct().getName();
        price = orderProduct.getOrderPrice();
        count = orderProduct.getCount();
        picture = orderProduct.getProduct().getPicture();
        orderStatus = orderProduct.getOrderStatus();
    }

    public OrderProductResponseDTO() {
    }

    //    public OrderProductResponseDTO(OrderProduct orderProduct, KakaoApproveResponse kakaoApprove) {
//        name = kakaoApprove.getItem_name();
//        price = kakaoApprove.getAmount().getTotal();
//        count = kakaoApprove.getQuantity();
//        picture = orderProduct.getProduct().getPicture();
//        deliverystatus = orderProduct.getOrders().getDeliveryStatus();
//    }

    public OrderProduct toEntity() {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(id);
        orderProduct.getProduct().setName(name);
        orderProduct.setOrderPrice(price);
        orderProduct.setCount(count);
        orderProduct.getProduct().setPicture(picture);
        orderProduct.setOrderStatus(orderStatus);
        return orderProduct;
    }
}
