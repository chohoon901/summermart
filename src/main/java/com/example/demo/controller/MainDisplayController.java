package com.example.demo.controller;

import com.example.demo.dto.MainDisplayDTO;
import com.example.demo.service.MainDisplayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainDisplayController {
      //<-- 수정 (객체 생성 누락)
    private final MainDisplayService mainDisplayService;
    @GetMapping("/display")
    public List<MainDisplayDTO> getTopProducts() {
        int numberOfProductsToDisplay = 10; // 표시할 상품 수
        return mainDisplayService.getTopProducts(numberOfProductsToDisplay);
    }
    @PostMapping("/display_product") //<-- 수정 (JSON 타입의 요청은 반드시 post 방식으로
    public MainDisplayDTO getProductById(
            @RequestBody MainDisplayDTO mainDisplayDTO /*
             수정
             기존 Map<String,Long> request 는
                       id    1 은 저장 가능하지만
                       name  미니미니  는 저장 불가 (미니미니를 long 숫자 로 저장하려고 할때 에러)
              */
    ) {
        Long id = mainDisplayDTO.getId(); // 수정 입력 아이디 리턴
//        System.out.println("Received id: " + id); // Add this line for debugging
        return mainDisplayService.getProductById(id); //아이디가 일치하는 물품 리턴
    }
}
