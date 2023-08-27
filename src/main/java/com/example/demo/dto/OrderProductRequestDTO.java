package com.example.demo.dto;

import com.example.demo.dto.kakao.KakaoApproveResponse;
import com.example.demo.entity.DeliveryStatus;
import com.example.demo.entity.OrderProduct;
import com.example.demo.entity.OrderStatus;
import lombok.Data;

@Data
public class OrderProductRequestDTO {
    private Long id;
    private String name;
    private int price;
    private int count;
    private String picture;
    private DeliveryStatus deliverystatus;
//    private OrderStatus orderStatus;

    public OrderProductRequestDTO(OrderProduct orderProduct) {
        id = orderProduct.getId();
        name = orderProduct.getProduct().getName();
        price = orderProduct.getOrderPrice();
        count = orderProduct.getCount();
        picture = orderProduct.getProduct().getPicture();
        deliverystatus = orderProduct.getOrders().getDeliveryStatus();
//        orderStatus = orderProduct.getOrders().getOrderStatus();
    }

    public OrderProductRequestDTO() {
    }

    //    public OrderProductRequestDTO(OrderProduct orderProduct, KakaoApproveResponse kakaoApprove) {
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
        orderProduct.getOrders().setDeliveryStatus(deliverystatus);
//        orderProduct.getOrders().setOrderStatus(orderStatus);
        return orderProduct;
    }
}
