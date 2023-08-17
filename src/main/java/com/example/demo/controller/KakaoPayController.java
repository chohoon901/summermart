package com.example.demo.controller;

import com.example.demo.dto.kakao.KakaoApproveResponse;
import com.example.demo.dto.kakao.KakaoResponseDTO;
import com.example.demo.service.kakaoPay.KakaoPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Transactional
@RequestMapping("/payment")
@RequiredArgsConstructor
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;

    /**
     * 결제요청
     */
    @PostMapping("/ready")
    public KakaoResponseDTO readyToKakaoPay(@RequestHeader Map<String,String> headers) {
        // TODO : @RequestBody로 react에서 json값을 받아와야함(상품명, 수량, 총 가격)

//        System.out.println("token = " + token);
        System.out.println("readyControllerQuery = ");
        System.out.println("headers="+headers);
        return kakaoPayService.kakaoPayReady();
    }

    @GetMapping("/success")
    public ResponseEntity afterPayRequest(@RequestParam("pg_token") String pgToken) {

        KakaoApproveResponse kakaoApprove = kakaoPayService.approveResponse(pgToken);
        // TODO : OrderItem 상품추가 메소드 부르기
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
