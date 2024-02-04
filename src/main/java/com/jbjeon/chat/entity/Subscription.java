package com.jbjeon.chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)                                                                      // AccessLevel.PROTECTED - 기본 생성자 개발자가 사용 안하는 것을 권장
public class Subscription {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Setter(AccessLevel.NONE)                                                                                             // AccessLevel.NONE - 해당 필드는 setter 생성 안함
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "MEMBER_ID")
  private Member member;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private SubscriptionType subscriptionType = SubscriptionType.NONE;                                                    // SubscriptionType.NONE - 구독 전(defaultValue)

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  @Column(columnDefinition = "int default 0", nullable = false)
  private BigDecimal amount = BigDecimal.ZERO;                                                                          // amount - 결제 금액, BigDecimal.ZERO - 0(defaultValue)

  public Subscription(Member member, SubscriptionType subscriptionType) {
    this.member = member;
    this.subscriptionType = subscriptionType;
  }
}
