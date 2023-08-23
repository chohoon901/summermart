package com.example.demo.controller;

import com.example.demo.dto.GetComparePasswordDTO;
import com.example.demo.dto.GetMemberResponseDTO;
import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/member")
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
        member.setRoles("USER");
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

    // 어디쓸지 잘 모름
    @GetMapping("/select_member")
    public GetMemberResponseDTO findMember(@AuthenticationPrincipal Member member) {
        return memberService.getMember(member);
    }

    @GetMapping("/select_AllMembers")
    public List<GetMemberResponseDTO> findMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/compare_password")
    public Boolean findMember(@AuthenticationPrincipal Member member,
                              @RequestBody GetComparePasswordDTO getComparePasswordDTO) {
        return memberService.comparePassword(member, getComparePasswordDTO);
    }


//    @DeleteMapping
//
//    @PutMapping
//
//    @PatchMapping

    // Member 보안문제로 id 값이 정해져 있음 (다른 사람 정보를 못가져옴)


}
