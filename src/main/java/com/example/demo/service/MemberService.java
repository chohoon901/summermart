//package com.example.demo.service;
//
//import com.example.demo.dto.MemberDTO;
//import com.example.demo.entity.Member;
//import com.example.demo.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class MemberService {
//
//    private final MemberRepository memberRepository;
//
//    // 회원 정보 생성
//    public void createMember(MemberDTO memberDTO) {
//        Member member = new Member();
//        member.setAddress(memberDTO.getAddress());
//        member.setMemberId(memberDTO.getMemberId());
//        member.setMemberPw(memberDTO.getMemberPw());
//        member.setMemberName(memberDTO.getMemberName());
//        member.setPhone(memberDTO.getPhone());
//        member.setRoles(memberDTO.getRoles());
//
//        memberRepository.save(member);
//    }
//
//    // 회원 정보 수정
//    public void updateMember(Long memberId, MemberDTO memberDTO) {
//        Member member = memberRepository.findById(memberId).orElse(null);
//
//        if (member != null) {
//            member.setAddress(memberDTO.getAddress());
//            member.setMemberPw(memberDTO.getMemberPw());
//            member.setMemberName(memberDTO.getMemberName());
//            member.setPhone(memberDTO.getPhone());
//            member.setRoles(memberDTO.getRoles());
//
//            memberRepository.save(member);
//        }
//    }
//
//    // 모든 회원 정보 조회
//    public List<MemberDTO> getAllMembers() {
//        return memberRepository.findAll()
//                .stream()
//                .map(member -> new MemberDTO(member))
//                .collect(Collectors.toList());
//    }
//
//    // 회원 정보 삭제
//    public void deleteMember(Long memberId) {
//        memberRepository.deleteById(memberId);
//    }
//}
//
