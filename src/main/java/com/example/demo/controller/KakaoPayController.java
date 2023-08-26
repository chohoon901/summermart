package com.example.demo.controller;

import com.example.demo.dto.OrderProductRequestDTO;
import com.example.demo.dto.kakao.KakaoApproveResponse;
import com.example.demo.dto.kakao.KakaoResponseDTO;
import com.example.demo.dto.kakao.PostKakaoRequestDTO;
import com.example.demo.entity.OrderProduct;
import com.example.demo.service.OrderProductService;
import com.example.demo.service.kakaoPay.KakaoPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    private final OrderProductService orderProductService;

    /**
     * 결제요청
     */
    @PostMapping("/ready")
    public KakaoResponseDTO readyToKakaoPay(@RequestBody PostKakaoRequestDTO postKakaoRequestDTO) {

        return kakaoPayService.kakaoPayReady(postKakaoRequestDTO);
    }

    @GetMapping("/success")
    public ResponseEntity afterPayRequest(@RequestParam("pg_token") String pgToken) {

        KakaoApproveResponse kakaoApprove = kakaoPayService.approveResponse(pgToken);
        orderProductService.createOrderProduct(kakaoApprove);
        return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
    }


}
