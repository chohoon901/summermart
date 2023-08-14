package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @PostMapping("/create_member")
    public void createMember(@RequestBody Member member) {
        memberRepository.save(member);
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
