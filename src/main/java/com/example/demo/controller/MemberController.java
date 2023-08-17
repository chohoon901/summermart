package com.example.demo.controller;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberService memberService;

    @GetMapping("home")
    public String home() {
        return "<h1>home</h1>";
    }

    @GetMapping("admin/users")
    public List<Member> members(){
        return memberRepository.findAll();
    }

    @PostMapping("join")
    public String join(@RequestBody Member member) {
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        member.setRoles("ROLE_USER");
        memberRepository.save(member);
        return "회원가입완료";
    }

    @GetMapping("/test")
    public String testPrincipal(Authentication authentication) {
//        System.out.println("Authentication = " + SecurityContextHolder.getContext().getAuthentication().toString());
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal(); // 세션 가져오기
        System.out.println("authentication = " + principalDetails.getUsername());
        return principalDetails.getUsername();
    }


    @PostMapping("/post_memberDTO")
    public void createMemberDto(@RequestBody MemberDTO memberDTO) {
        memberService.createMember(memberDTO);
    }

//    @DeleteMapping
//
//    @PutMapping
//
//    @PatchMapping

    @GetMapping("/get_member")
    public List<MemberDTO> getMembers() {
        return memberService.getAllMembers();
    }
}
