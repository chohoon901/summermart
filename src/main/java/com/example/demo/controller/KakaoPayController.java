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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    private final OrderProductService orderProductService;

    /**
     * 결제요청
     */
    public KakaoResponseDTO readyToKakaoPay(@RequestHeader Map<String,String> headers) {

//        System.out.println("token = " + token);
        System.out.println("readyControllerQuery = ");
        System.out.println("headers="+headers);
        return kakaoPayService.kakaoPayReady(new PostKakaoRequestDTO());
    }

    @GetMapping("/success")
    public ResponseEntity afterPayRequest(@RequestParam("pg_token") String pgToken) {

        KakaoApproveResponse kakaoApprove = kakaoPayService.approveResponse(pgToken);
        orderProductService.createOrderProduct(kakaoApprove);
        return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
    }

//    /**
//     * 결제 진행 중 취소
//     */
//    @GetMapping("/cancel")
//    public void cancel() {
//
//        throw new BusinessLogicException(ExceptionCode.PAY_CANCEL);
//    }
//
//    /**
//     * 결제 실패
//     */
//    @GetMapping("/fail")
//    public void fail() {
//
//        throw new BusinessLogicException(ExceptionCode.PAY_FAILED);
//    }
}
