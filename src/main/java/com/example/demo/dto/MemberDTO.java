package com.example.demo.dto;

import com.example.demo.entity.Member;
import lombok.Data;

@Data
public class MemberDTO {

    private String address;
    private String memberId;
    private String memberPw;
    private String name;
    private String phone;
    private String roles;

    // MemberDTO 생성 메서드 예시 (Member 객체를 기반으로 DTO 생성)
    public MemberDTO(Member member) {
        address = member.getAddress();
        memberId = member.getMemberId();
        memberPw = member.getMemberPw();
        name = member.getName();
        phone = member.getPhone();
        roles = member.getRoles();

    }
}