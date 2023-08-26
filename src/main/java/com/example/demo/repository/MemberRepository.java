package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA는 DB에 접근
//jpa를 사용하면 추가적인 함수 선언이 필요없다.
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 커스텀 함수선언
    // 사용하는 때 JPArepository안에 없는 함수를 만들어야 할떄
    // 안에 있는 함수 예)save, findAll, findById... 자주 쓰임
    // 위의 3개 말고 (저장, 전체찾기, ID별 단일찾기(기본내장)) 다른 기능이 필요하면 질문
    Member findByUsername(String username);

}
