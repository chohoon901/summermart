package com.example.demo.dto;


import com.example.demo.entity.Member;
import lombok.Data;

// 회원가입 요청DTO
// POST 반환 X (생성전용)
// 필요한 변수 생성
// 변수 안에 Request 값들이 담김
// 변수 집합
@Data
// 엔티티 값을 불러옴 (getter)
// 엔티티 값을 설정함 (setter)

//MemberRequestDTO ( = JSON)
public class MemberRequestDTO {
    // 변수선언
    private String address;
    private String username;
    private String password;
    private String name;
    private String phone;

    // a = 1
//    public MemberRequestDTO(Member member) {
//        address = member.getAddress();
//        username = member.getUsername();
//        password = member.getPassword();
//        name = member.getName();
//        phone = member.getPhone();
//
//    }



}