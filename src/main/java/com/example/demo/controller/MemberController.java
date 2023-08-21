package com.example.demo.controller;

import com.example.demo.dto.GetMemberResponseDTO;
import com.example.demo.dto.MemberRequestDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
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

    // 리엑트에서 요청한 JSON이 RequestBody
    // get 요청도 마찬가지
    @PostMapping("/create_member")
    public void createMember(@RequestBody MemberRequestDTO memberRequestDTO) {
        memberService.createMember(memberRequestDTO);
    }

    // RequestParam 사용 금지 하위호한
    // Product Path Variable 사용, 주소의 id가 들어있을 때
//    @GetMapping("/select_member/{id}")
//    public GetMemberResponseDTO findMember(@PathVariable Long id) {
//        return memberService.getMember(id);
//    }

    // Member 보안문제로 id 값이 정해져 있음 (다른 사람 정보를 못가져옴)
    @GetMapping("/select_member")
    public GetMemberResponseDTO findMember() {
        return memberService.getMember();
    }
}
