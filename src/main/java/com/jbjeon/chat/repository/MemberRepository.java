package com.jbjeon.chat.repository;

import com.jbjeon.chat.entity.Gender;
import com.jbjeon.chat.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
  List<Member> findByUsername(String name);
  List<Member> findByGender(Gender gender);
  List<Member> findByBirthday(String birthday);
  List<Member> findByPhoneNumber(String phoneNumber);
  List<Member> findByEmail(String email);
  @Query("select m from Member m where cast(m.createdDate as DATE) = :searchDate")
  List<Member> findByCreatedDate(@Param("searchDate") LocalDate createdDate);                                           // '년월일' 검색
}
