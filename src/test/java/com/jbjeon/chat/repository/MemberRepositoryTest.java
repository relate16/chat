package com.jbjeon.chat.repository;

import com.jbjeon.chat.entity.Gender;
import com.jbjeon.chat.entity.Member;
import com.jbjeon.chat.exception.CustomAlgorithmException;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional                                                                                                          // @Transactional - 테스트가 끝나면 롤백해주는 역할
class MemberRepositoryTest {

  @Autowired
  private EntityManager em;
  @Autowired
  private MemberRepository memberRepository;

  @Test
  void save() {
    String password = "password1";
    String passwordHashValue = getHashValue(password);

    Member member = new Member("username1", passwordHashValue,
      Gender.M,"920130","010-0000-0000","test@google.com", LocalDateTime.now());

    Member saveMember = memberRepository.save(member);

    Assertions.assertThat(saveMember.getId()).isEqualTo(member.getId());                                                // save() 메서드 검증
  }

  @Test
  void findByUsername() {
    String password = "password1";
    String passwordHashValue = getHashValue(password);

    String findUsername = "username1";

    Member member = new Member(findUsername, passwordHashValue,
      Gender.M,"920130","010-0000-0000","test@google.com", LocalDateTime.now());
    Member saveMember = memberRepository.save(member);

    em.flush();                                                                                                         // '쓰기 지연 sql' db에 전송
    em.clear();                                                                                                         // 영속성 컨텍스트 초기화

    boolean contains = em.contains(saveMember);                                                                         // 영속성 컨텍스트가 초기화 됐는지 확인
    Assertions.assertThat(contains).isFalse();                                                                          // :

    List<Member> findMembers = memberRepository.findByUsername(findUsername);

    for (Member findMember : findMembers) {                                                                             // findByUsername() 메서드 검증
      Assertions.assertThat(findMember.getUsername()).isEqualTo(saveMember.getUsername());                              // :
    }                                                                                                                   // :
  }



  @Test
  void findByGender() {
    String password = "password1";
    String passwordHashValue = getHashValue(password);

    Gender findGender = Gender.M;

    Member member = new Member("username1", passwordHashValue,
      findGender,"920130","010-0000-0000","test@google.com", LocalDateTime.now());
    Member saveMember = memberRepository.save(member);

    em.flush();                                                                                                         // '쓰기 지연 sql' db에 전송
    em.clear();                                                                                                         // 영속성 컨텍스트 초기화

    List<Member> findMembers = memberRepository.findByGender(findGender);
    for (Member findMember : findMembers) {                                                                             // findByGender() 메서드 검증
      Assertions.assertThat(findMember.getGender()).isEqualTo(saveMember.getGender());                                  // :
    }                                                                                                                   // :
  }

  @Test
  void findByBirthday() {
    String password = "password1";
    String passwordHashValue = getHashValue(password);

    String findBirthday = "920130";

    Member member = new Member("username1", passwordHashValue,
      Gender.M, findBirthday,"010-0000-0000","test@google.com", LocalDateTime.now());
    Member saveMember = memberRepository.save(member);

    em.flush();                                                                                                         // '쓰기 지연 sql' db에 전송
    em.clear();                                                                                                         // 영속성 컨텍스트 초기화

    List<Member> findMembers = memberRepository.findByBirthday(findBirthday);
    for (Member findMember : findMembers) {                                                                             // findByBirthday() 메서드 검중
      Assertions.assertThat(findMember.getBirthday()).isEqualTo(saveMember.getBirthday());                              // :
    }                                                                                                                   // :
  }

  @Test
  void findByPhoneNumber() {
    String password = "password1";
    String passwordHashValue = getHashValue(password);

    String findPhoneNumber = "010-0000-0000";

    Member member = new Member("username1", passwordHashValue,
      Gender.M,"920130", findPhoneNumber,"test@google.com", LocalDateTime.now());
    Member saveMember = memberRepository.save(member);

    em.flush();                                                                                                         // '쓰기 지연 sql' db에 전송
    em.clear();                                                                                                         // 영속성 컨텍스트 초기화

    List<Member> findMembers = memberRepository.findByPhoneNumber(findPhoneNumber);

    for (Member findMember : findMembers) {                                                                             // findByPhoneNumber() 메서드 검증
      Assertions.assertThat(findMember.getPhoneNumber()).isEqualTo(saveMember.getPhoneNumber());                        // :
    }                                                                                                                   // :
  }

  @Test
  void findByEmail() {
    String password = "password1";
    String passwordHashValue = getHashValue(password);

    String findEmail = "test@google.com";

    Member member = new Member("username1", passwordHashValue,
      Gender.M,"920130","010-0000-0000", findEmail, LocalDateTime.now());
    Member saveMember = memberRepository.save(member);

    em.flush();                                                                                                         // '쓰기 지연 sql' db에 전송
    em.clear();                                                                                                         // 영속성 컨텍스트 초기화

    List<Member> findMembers = memberRepository.findByEmail(findEmail);

    for (Member findMember : findMembers) {                                                                             // findByEmail() 메서드 검증
      Assertions.assertThat(findMember.getEmail()).isEqualTo(saveMember.getEmail());                                    // :
    }                                                                                                                   // :
  }

  @Test
  void findByCreatedDateLocalDate() {
    String password = "password1";
    String passwordHashValue = getHashValue(password);

    LocalDateTime findDate = LocalDateTime.now();

    Member member = new Member("username1", passwordHashValue,
      Gender.M,"920130","010-0000-0000","test@google.com", findDate);
    Member saveMember = memberRepository.save(member);

    em.flush();                                                                                                         // '쓰기 지연 sql' db에 전송
    em.clear();                                                                                                         // 영속성 컨텍스트 초기화

    List<Member> findMembers = memberRepository.findByCreatedDate(findDate.toLocalDate());                              // findByCreatedDate() - '년월일' 검색

    for (Member findMember : findMembers) {                                                                             // findByCreatedDate(LocalDate createdDate) 메서드 검증
      Assertions.assertThat(findMember.getCreatedDate().toLocalDate())                                                  // :
        .isEqualTo(saveMember.getCreatedDate().toLocalDate());                                                          // :
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
   *        Gender.M,"920130","010-0000-0000","test@google.com", LocalDateTime.now());
   *    </pre>
   * @return String hashValue
   * @throws CustomAlgorithmException 암호화에 필요한 알고리즘을 찾을 수 없는 경우 발생
   */
  private String getHashValue(String string) {                                                                          // 해시 값을 구하는 함수(SHA-256)
    String hashValue = null;                                                                                            // : hashValue - 해시 값 문자열(=return 값)
    MessageDigest mdSha256 = null;                                                                                      // : mdSha256 - SHA-256 해시값
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