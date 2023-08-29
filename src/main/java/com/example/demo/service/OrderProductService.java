package com.example.demo.service;

import com.example.demo.dto.OrderProductResponseDTO;
import com.example.demo.dto.RequestOrderStatusDTO;
import com.example.demo.dto.kakao.KakaoApproveResponse;
import com.example.demo.entity.Member;
import com.example.demo.entity.OrderProduct;
import com.example.demo.entity.OrderStatus;
import com.example.demo.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    // 리스트일 경우 map을 통해 DTO를 Entity로 바꿈
    public List<OrderProductResponseDTO> getOrderProducts (Long memberId) {
        return orderProductRepository.findByMemberId(memberId)
                .stream()
                .map(OrderProductResponseDTO::new)
                .collect(Collectors.toList());
    }

    public void createOrderProduct(KakaoApproveResponse kakaoApprove) {
        OrderProductResponseDTO orderProductResponseDTO = new OrderProductResponseDTO();
        OrderProduct orderProduct = orderProductResponseDTO.toEntity();
        orderProduct.getProduct().setName(kakaoApprove.getItem_name());
        orderProduct.setCount(kakaoApprove.getQuantity());
        orderProduct.setOrderPrice(kakaoApprove.getAmount().getTotal());
        orderProductRepository.save(orderProduct);
    }

    public void updateOrderStatus(RequestOrderStatusDTO requestOrderStatusDTO) {
        Long orderItemId = requestOrderStatusDTO.getOrderItemId();

        // 주어진 orderItemId로 OrderProduct을 찾습니다.
        // optional을 사용할 때
        Optional<OrderProduct> orderProductOptional = orderProductRepository.findById(orderItemId);


        // optional을 사용할 때
        if (orderProductOptional.isPresent()) {
            OrderProduct orderProduct = orderProductOptional.get();

//        if (!orderProducts.isEmpty()) {
//            OrderProduct orderProduct = orderProducts.get(0);

            // 현재 상태에 따라 OrderStatus를 전환합니다.
            if (orderProduct.getOrderStatus() == OrderStatus.ORDER) {
                orderProduct.setOrderStatus(OrderStatus.CANCEL);
            } else {
                orderProduct.setOrderStatus(OrderStatus.ORDER);
            }
            // 변경된 OrderProduct을 저장합니다.
            orderProductRepository.save(orderProduct);
        } else {
            throw new EntityNotFoundException("ID가 " + orderItemId + "인 OrderProduct를 찾을 수 없습니다.");
        }
    }
}
