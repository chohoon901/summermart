package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 정보 생성
    public void createMember(MemberDTO memberDTO) {
        Member member = new Member();
        member.setAddress(memberDTO.getAddress());
        member.setUsername(memberDTO.getUsername());
        member.setPassword(memberDTO.getPassword());
        member.setName(memberDTO.getName());
        member.setPhone(memberDTO.getPhone());
        member.setRoles(memberDTO.getRoles());
        memberRepository.save(member);
    }

    // 모든 회원 정보 조회
    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberDTO::new)
                .collect(Collectors.toList());
    }
}
