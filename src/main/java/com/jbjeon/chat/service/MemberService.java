package com.jbjeon.chat.service;

import com.jbjeon.chat.entity.Gender;
import com.jbjeon.chat.entity.Member;
import com.jbjeon.chat.exception.CustomAlgorithmException;
import com.jbjeon.chat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

  private final MemberRepository memberRepository;

  public Member saveMember(String username, String password, Gender gender, String birthday, String phoneNumber, String email) {
    String passwordHashValue = getHashValue(password);

    Member member = new Member(username, passwordHashValue, gender, birthday, phoneNumber, email, LocalDateTime.now());

    return memberRepository.save(member);
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
