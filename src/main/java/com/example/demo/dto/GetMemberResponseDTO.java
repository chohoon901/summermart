package com.example.demo.dto;

import com.example.demo.entity.Member;
import lombok.Data;

@Data
public class GetMemberResponseDTO {
    private String address;
    private String username;
    private String password;
    private String name;
    private String phone;


    // 다중 조회에서 사용하기 위해 작성
    public GetMemberResponseDTO(Member member) {
        address = member.getAddress();
        username = member.getUsername();
        password = member.getPassword();
        name = member.getName();
        phone = member.getPhone();

    }
}
