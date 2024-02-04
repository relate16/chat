package com.jbjeon.chat.service;

import com.jbjeon.chat.entity.Gender;
import com.jbjeon.chat.entity.Member;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

  @Autowired
  private EntityManager em;
  @Autowired
  private MemberService memberService;

  @Test
  void saveMember() {
    Member saveMember =
      memberService.saveMember("username1", "password1", Gender.M, "920130", "010-0000-0000", "test@gmail.com");

    Assertions.assertThat(saveMember.getId()).isNotNull();                                                              // saveMember 검증 로직
  }

}