package com.example.demo.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.config.CustomBCryptPasswordEncoder;
import com.example.demo.config.jwt.JwtProperties;
import com.example.demo.config.oauth.provider.GoogleUser;
import com.example.demo.config.oauth.provider.OAuthUserInfo;
import com.example.demo.dto.OauthMemberDto;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OAuthController {

    private final MemberRepository memberRepository;
    private final CustomBCryptPasswordEncoder customBCryptPasswordEncoder;



    @PostMapping("/oauth/jwt/google")
    public String jwtCreate(@RequestBody Map<String, Object> data) {
        System.out.println("jwtCreate 실행됨");
        System.out.println(data);
        OAuthUserInfo googleUser = new GoogleUser(data);

        Member userEntity =
                memberRepository.findByUsername(googleUser.getProvider()+"_"+googleUser.getProviderId());

        if(userEntity == null) {
            OauthMemberDto memberDto = new OauthMemberDto();
            memberDto.setUsername(googleUser.getProvider() + "_" + googleUser.getProviderId());
            memberDto.setPassword(customBCryptPasswordEncoder.encode("abc"));
            memberDto.setProvider(googleUser.getProvider());
            memberDto.setProviderId(googleUser.getProviderId());
            memberDto.setRoles("USER");
            Member member = Member.createOauthMember(memberDto.getUsername(), memberDto.getPassword(),
                    memberDto.getProvider(), memberDto.getProviderId(), memberDto.getRoles());

            userEntity = memberRepository.save(member);
        }

        String jwtToken = JWT.create()
                .withSubject(userEntity.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME))
                .withClaim("id", userEntity.getId())
                .withClaim("username", userEntity.getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        return jwtToken;
    }
}
