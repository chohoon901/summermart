package com.example.demo.dto;

import com.example.demo.entity.Member;
import lombok.Data;

import java.util.List;

@Data
public class MemberDTO {

    private String address;
    private String memberId;
    private String memberPw;
    private String name;
    private String phone;
    private String roles;

    public MemberDTO() {
        // 기본 생성자 추가
    }


    public MemberDTO(Member member) {
        address = member.getAddress();
        memberId = member.getMemberId();
        memberPw = member.getMemberPw();
        name = member.getName();
        phone = member.getPhone();
        roles = member.getRoles();
    }
}