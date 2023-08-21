package com.example.demo.service;

import com.example.demo.dto.OrderProductRequestDTO;
import com.example.demo.dto.kakao.KakaoApproveResponse;
import com.example.demo.entity.OrderProduct;
import com.example.demo.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    // 리스트일 경우 map을 통해 DTO를 Entity로 바꿈
    public List<OrderProductRequestDTO> getOrderProducts () {
        return orderProductRepository.findAllByMember_Id(1L)
                .stream()
                .map(OrderProductRequestDTO::new)
                .collect(Collectors.toList());
    }

    public void createOrderProduct(KakaoApproveResponse kakaoApprove) {
        OrderProductRequestDTO orderProductRequestDTO = new OrderProductRequestDTO();
        OrderProduct orderProduct = orderProductRequestDTO.toEntity();
        orderProduct.getProduct().setName(kakaoApprove.getItem_name());
        orderProduct.setCount(kakaoApprove.getQuantity());
        orderProduct.setOrderPrice(kakaoApprove.getAmount().getTotal());
        orderProductRepository.save(orderProduct);
    }



}
