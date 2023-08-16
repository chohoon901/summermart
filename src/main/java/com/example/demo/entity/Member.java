package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToMany(mappedBy = "member")
    private List<Orders> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MyLike> myLikes = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    private String address;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String roles;
    private String providerId;
    private String provider;
//    private int point;

    public static Member createOauthMember(String username, String password, String provider, String providerId, String roles) {
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(password);
        member.setProvider(provider);
        member.setProviderId(providerId);
        member.setRoles(roles);

        return member;
    }
}
