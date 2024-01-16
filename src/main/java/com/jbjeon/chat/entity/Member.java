package com.jbjeon.chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Setter(AccessLevel.NONE)                                                                                             // 해당 필드는 setter 생성 안함
  private Long id;

  @Column(columnDefinition = "varchar(50) default 'EMPTY'", nullable = false)
  private String username;

  @Column(columnDefinition = "varchar(255) default 'EMPTY'", nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "char(1) default 'E'", nullable = false)                                                                     // 'E' : EMPTY
  private Gender gender;

  @Column(columnDefinition = "char(6) default '000000'", nullable = false)
  private String birthday;

  @Column(columnDefinition = "varchar(20) default 'EMPTY'", nullable = false)
  private String phoneNumber;

  @Column(columnDefinition = "varchar(255) default 'EMPTY'", nullable = false)
  private String email;

  private LocalDateTime createdDate;

  public Member(String username, String password, Gender gender, String birthday,
                String phoneNumber, String email, LocalDateTime createdDate) {
    this.username = username;
    this.password = password;
    this.gender = gender;
    this.birthday = birthday;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.createdDate = createdDate;
  }
}
