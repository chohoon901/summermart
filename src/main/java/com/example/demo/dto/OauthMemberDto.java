package com.example.demo.dto;

import lombok.Data;

@Data
public class OauthMemberDto {

    private String username;
    private String password;
    private String provider;
    private String providerId;
    private String roles;
}
