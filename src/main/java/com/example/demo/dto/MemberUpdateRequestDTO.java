package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberUpdateRequestDTO {
    private Long id;
    private String address;
    private String username;
    private String password;
    private String name;
    private String phone;

    // @Builder 결과 (위에서 자동으로 해줌)
//    public MemberUpdateRequestDTO(Long id, String username, String password, String address, String name, String phone) {
//        this.id = id;
//        this.address = address;
//        this.username = username;
//        this.password = password;
//        this.name = name;
//        this.phone = phone;
//    }

}
