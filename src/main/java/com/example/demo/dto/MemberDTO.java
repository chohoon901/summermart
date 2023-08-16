package com.example.demo.dto;

import com.example.demo.entity.Member;
import lombok.Data;

@Data
public class MemberDTO {

    private String address;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String roles;

    public MemberDTO() {
        // 기본 생성자 추가
    }


    public MemberDTO(Member member) {
        address = member.getAddress();
        username = member.getUsername();
        password = member.getPassword();
        name = member.getName();
        phone = member.getPhone();
        roles = member.getRoles();
    }
}