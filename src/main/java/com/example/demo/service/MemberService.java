package com.example.demo.service;

import com.example.demo.dto.GetComparePasswordDTO;
import com.example.demo.dto.GetMemberResponseDTO;
import com.example.demo.dto.MemberRequestDTO;
import com.example.demo.dto.MemberUpdateRequestDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// 컨트롤러 -> 서비스 -> 레퍼지토리 -> 엔티티(DB)
// 엔티티 -> 레퍼지토리(JPA로 실제 기능 정의) -> (레퍼지토리에서 기능을 가져와서 사용)서비스 -> (서비스에서 함수를 가져와서 사용)컨트롤러
// DTO는 어디서 불러오는가 -> Controller, Service -> 하나의 요청(API)에는 하나의 DTO가 있다.
// 요청이나 응답 한개당 DTO 한개
// POST는 단방향, GET은 갔다가 오는것
// 대부분 findByID 사용
// Patch -> POST와 동일 patch는 다른 값을 저장
// Request Response 작성 (구분해두기)
// autowired X
// RequiredArgsConstructor 사용

@Service
@RequiredArgsConstructor
// 생성자 생성 (연결) RequiredArgsConstructor
public class MemberService {

    // autowired + 추가기능
    // memberRepository와 연결
    // 서비스랑 필요한 repository 연결 -> RequiredArgsConstructor + private final
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 기능 하나 당 API 한개 ex) 저장은 저장기능 한개만
    // MemberDTO라는 음식을 Member라는 그릇에 담아서 save라는 전자레인지에 넣는다
    // 매개변수 원시타입 (int, string, float, long...) 인 경우 음식
    // 원시타입이 아니라 클래스 타입일 경우 그릇
    // 한 명만 데이터베이스에 담는다 List (x)
    // void = return 사용하지 않음
    public void createMember(MemberRequestDTO memberRequestDTO) {

        Member member = new Member();

        member.setAddress(memberRequestDTO.getAddress());
        member.setUsername(memberRequestDTO.getUsername());
        member.setPassword(memberRequestDTO.getPassword());
        member.setName(memberRequestDTO.getName());
        member.setPhone(memberRequestDTO.getPhone());

        memberRepository.save(member);

        // memberRepository.deleteById(memberId); 음식을 매개변수로 맡는다

        // MemberRepository 전자레인지에는 Member 그릇밖에 못들어간다 (DTO는 못들어간다)

    }

    // List 여러 개를 리스트에 담는다
    // 반환값은 함수 형태랑 같아야 한다.
    public List<GetMemberResponseDTO> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(GetMemberResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Optional : null 값 허용 (빈칸 허용)
    // 단건 조회
    // 그릇을 옮기는 작업과 그릇을 만드는 작업을 한줄에서 다함
    // Member에서는 보안문제 때문에 매개변수 작성 금지 (다른 사람 정보 가져오기 금지)
    public GetMemberResponseDTO getMember(Member member) {
        Member member1 = memberRepository.findByUsername(member.getUsername());
        return new GetMemberResponseDTO(member1);

    }

    public Boolean comparePassword(GetComparePasswordDTO getComparePasswordDTO) {
        boolean isCorrect = false;

        boolean passwordsMatch = new BCryptPasswordEncoder().matches(getComparePasswordDTO.getPassword(),
                memberRepository.findById(getComparePasswordDTO.getMemberId()).orElseThrow().getPassword());
        if(passwordsMatch) {
            isCorrect = true;
        }
        return isCorrect;
    }


    // Member 말고 다른 곳에서 사용하게될 단건 조회 요청
//    public GetMemberResponseDTO getMember(Long id) {
//        Member member = memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 회원이 없습니다. id="+id));
//
//        return new GetMemberResponseDTO(member);
//    }

    public void updateMember(MemberUpdateRequestDTO memberUpdateRequestDTO) {
        Member member = memberRepository.findById(memberUpdateRequestDTO.getId())
                .orElseThrow(()->new IllegalArgumentException("해당 회원이 없습니다. id="+memberUpdateRequestDTO.getId()));
//        Member member = memberRepository.findById(3L)
//                .orElseThrow(()->new IllegalArgumentException("해당 회원이 없습니다. id="+3L));

        if(memberUpdateRequestDTO.getAddress()!=null){
            member.setAddress(memberUpdateRequestDTO.getAddress());
        }
        if(memberUpdateRequestDTO.getUsername()!=null){
            member.setUsername(memberUpdateRequestDTO.getUsername());
        }
        if(memberUpdateRequestDTO.getPassword()!=null){
            member.setPassword(bCryptPasswordEncoder.encode(memberUpdateRequestDTO.getPassword()));
        }
        if(memberUpdateRequestDTO.getName()!=null){
            member.setName(memberUpdateRequestDTO.getName());
        }
        if(memberUpdateRequestDTO.getUsername()!=null){
            member.setPhone(memberUpdateRequestDTO.getPhone());
        }

//        member.update(memberUpdateRequestDTO.getAddress(),
//                      memberUpdateRequestDTO.getUsername(),
//                      memberUpdateRequestDTO.getPassword(),
//                      memberUpdateRequestDTO.getName(),
//                      memberUpdateRequestDTO.getPhone());

        memberRepository.save(member);
    }

}
