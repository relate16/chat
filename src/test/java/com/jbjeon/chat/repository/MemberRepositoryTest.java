package com.jbjeon.chat.repository;

import com.jbjeon.chat.entity.Gender;
import com.jbjeon.chat.entity.Member;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional                                                                                                          // 테스트가 끝나면 롤백해주는 역할
class MemberRepositoryTest {

  @Autowired
  private EntityManager em;
  @Autowired
  private MemberRepository memberRepository;

  @Test
  void save() {
    Member member = new Member("username1", "password1",
      Gender.M,"920130","010-0000-0000","test@google.com", LocalDateTime.now());

    Member saveMember = memberRepository.save(member);

    Assertions.assertThat(saveMember.getId()).isEqualTo(member.getId());
  }

  @Test
  void findByName() {
    Member member = new Member("username1", "password1",                                           //
      Gender.M,"920130","010-0000-0000","test@google.com", LocalDateTime.now());        //

    Member saveMember = memberRepository.save(member);

    em.flush();                                                                                                         // '쓰기 지연 sql' db에 전송
    em.clear();                                                                                                         // 영속성 컨텍스트 초기화

    boolean contains = em.contains(saveMember);                                                                         // 영속성 컨텍스트가 초기화 됐는지 확인
    Assertions.assertThat(contains).isFalse();                                                                          // :

    Optional<Member> findMemberOt = memberRepository.findByUsername("username1");                                       // findByUsername() 메서드 검증
    Member findMember = findMemberOt.orElseThrow(() -> new RuntimeException("cant find Member"));                       // :
    Assertions.assertThat(findMember.getId()).isEqualTo(saveMember.getId());                                            // :
  }
}