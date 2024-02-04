package com.jbjeon.chat.repository;

import com.jbjeon.chat.entity.Gender;
import com.jbjeon.chat.entity.Member;
import com.jbjeon.chat.exception.CustomAlgorithmException;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional                                                                                                          // @Transactional - 테스트가 끝나면 롤백해주는 역할
class MemberRepositoryTest {

  @Autowired
  private EntityManager em;
  @Autowired
  private MemberRepository memberRepository;

  @BeforeEach
  void init() {
    String password = "password1";
    String passwordHashValue = getHashValue(password);

    String findUsername = "username1";
    Gender findGender = Gender.M;
    String findBirthday = "920130";
    String findPhoneNumber = "010-0000-0000";
    String findEmail = "test1@gmail.com";
    Member member = new Member(findUsername, passwordHashValue,
      findGender, findBirthday, findPhoneNumber, findEmail, LocalDateTime.now());

    Member saveMember = memberRepository.save(member);

    Assertions.assertThat(saveMember.getId()).isNotNull();                                                              // save 됐는지 검증

    em.flush();                                                                                                         // '쓰기 지연 sql' db에 전송
    em.clear();                                                                                                         // 영속성 컨텍스트 초기화

    boolean containMember = em.contains(saveMember);                                                                    // 영속성 컨텍스트가 초기화 됐는지 확인
    Assertions.assertThat(containMember).isFalse();                                                                     // :
  }

  @Test
  void findByUsername() {
    String findUsername = "username1";

    List<Member> findMembers = memberRepository.findByUsername(findUsername);
    for (Member findMember : findMembers) {                                                                             // findByUsername() 메서드 검증
      Assertions.assertThat(findMember.getUsername()).isEqualTo(findUsername);                                          // :
    }                                                                                                                   // :
  }

  @Test
  void findByGender() {
    Gender findGender = Gender.M;

    List<Member> findMembers = memberRepository.findByGender(findGender);
    for (Member findMember : findMembers) {                                                                             // findByGender() 메서드 검증
      Assertions.assertThat(findMember.getGender()).isEqualTo(findGender);                                              // :
    }                                                                                                                   // :
  }

  @Test
  void findByBirthday() {
    String findBirthday = "920130";

    List<Member> findMembers = memberRepository.findByBirthday(findBirthday);
    for (Member findMember : findMembers) {                                                                             // findByBirthday() 메서드 검중
      Assertions.assertThat(findMember.getBirthday()).isEqualTo(findBirthday);                                          // :
    }                                                                                                                   // :
  }

  @Test
  void findByPhoneNumber() {
    String findPhoneNumber = "010-0000-0000";

    List<Member> findMembers = memberRepository.findByPhoneNumber(findPhoneNumber);

    for (Member findMember : findMembers) {                                                                             // findByPhoneNumber() 메서드 검증
      Assertions.assertThat(findMember.getPhoneNumber()).isEqualTo(findPhoneNumber);                                    // :
    }                                                                                                                   // :
  }

  @Test
  void findByEmail() {
    String findEmail = "test1@gmail.com";

    List<Member> findMembers = memberRepository.findByEmail(findEmail);
    for (Member findMember : findMembers) {                                                                             // findByEmail() 메서드 검증
      Assertions.assertThat(findMember.getEmail()).isEqualTo(findEmail);                                                // :
    }                                                                                                                   // :
  }

  @Test
  void findByCreatedDateLocalDate() {
    LocalDate findDate = LocalDateTime.now().toLocalDate();

    List<Member> findMembers = memberRepository.findByCreatedDate(findDate);                                            // findByCreatedDate() - '년월일' 검색
    for (Member findMember : findMembers) {                                                                             // findByCreatedDate(LocalDate createdDate) 메서드 검증
      Assertions.assertThat(findMember.getCreatedDate().toLocalDate()).isEqualTo(findDate);                             // :
    }                                                                                                                   // :
  }


  // private 메서드 목록

  /**
   * 사용 예 :
   *    <pre>
   *      String password = "password1";
   *
   *      String passwordHashValue = getHashValue(password);
   *
   *      Member member = new Member("username1", passwordHashValue,
   *        Gender.M,"920130","010-0000-0000","test1@gmail.com", LocalDateTime.now());
   *    </pre>
   * @return String hashValue
   * @throws CustomAlgorithmException 암호화에 필요한 알고리즘을 찾을 수 없는 경우 발생
   */
  private String getHashValue(String string) {                                                                          // 해시 값을 구하는 함수(SHA-256)
    String hashValue;                                                                                                   // : hashValue - 해시 값 문자열(=return 값)
    MessageDigest mdSha256;                                                                                             // : mdSha256 - SHA-256 해시값
    try {                                                                                                               // : SHA-256 해시값 생성
      mdSha256 = MessageDigest.getInstance("SHA-256");                                                                  // : :
      mdSha256.update(string.getBytes(StandardCharsets.UTF_8));                                                         // : : MessageDigest 에 해싱할 데이터를 입력
                                                                                                                        // : :
      byte[] hashByteSha256 = mdSha256.digest();                                                                        // : : 해시 값 바이트 배열로 생성
                                                                                                                        // : :
      StringBuilder hashStringSha256 = new StringBuilder();                                                             // : : 바이트 배열인 해시 값(=hashByteSha256) 문자열로 변환
      for(byte element : hashByteSha256) {                                                                              // : : :
        String elementString = String.format("%02x", element);                                                          // : : : "%02x" - 16진수 (숫자, 영문, 한글, 특수문자 표현)
        hashStringSha256.append(elementString);                                                                         // : : :
      }                                                                                                                 // : : :
                                                                                                                        // :
      hashValue = hashStringSha256.toString();                                                                          // :
    } catch (NoSuchAlgorithmException e) {                                                                              // : 체크 예외 런타임 예외로 변환
      throw new CustomAlgorithmException("Error during encryption: Algorithm not found : ", e);                         // : :
    }                                                                                                                   // : :
    return hashValue;                                                                                                   // :
  }                                                                                                                     // :
}