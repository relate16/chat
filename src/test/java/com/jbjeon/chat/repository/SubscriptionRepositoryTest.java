package com.jbjeon.chat.repository;

import com.jbjeon.chat.entity.Gender;
import com.jbjeon.chat.entity.Member;
import com.jbjeon.chat.entity.Subscription;
import com.jbjeon.chat.entity.SubscriptionType;
import com.jbjeon.chat.service.MemberService;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional                                                                                                          // @Transactional - 테스트가 끝나면 롤백해주는 역할
class SubscriptionRepositoryTest {
  @Autowired
  private EntityManager em;
  @Autowired
  private MemberService memberService;
  @Autowired
  private SubscriptionRepository subscriptionRepository;

  @BeforeEach
  void init() {
    Member saveMember =
      memberService.saveMember("username1", "password1", Gender.M, "920130", "010-0000-0000", "test1@gmail.com");
    SubscriptionType findSubscriptionType = SubscriptionType.NONE;
    Subscription saveSubscription = subscriptionRepository.save(new Subscription(saveMember, findSubscriptionType));

    Assertions.assertThat(saveMember.getId()).isNotNull();                                                              // save 됐는지 검증
    Assertions.assertThat(saveSubscription.getId()).isNotNull();                                                        // :

    em.flush();                                                                                                         // '쓰기 지연 sql' db에 전송
    em.clear();                                                                                                         // 영속성 컨텍스트 초기화

    boolean containMember = em.contains(saveMember);                                                                    // 영속성 컨텍스트가 초기화 됐는지 확인
    Assertions.assertThat(containMember).isFalse();                                                                     // :
    boolean containSubscription = em.contains(saveSubscription);                                                        // :
    Assertions.assertThat(containSubscription).isFalse();                                                               // :
  }

  @Test
  void findBySubscriptionType() {
    SubscriptionType findSubscriptionType = SubscriptionType.NONE;

    List<Subscription> findSubscriptions = subscriptionRepository.findBySubscriptionType(findSubscriptionType);
    for (Subscription subscription : findSubscriptions) {                                                               // findBySubscriptionType() 검증
      Assertions.assertThat(subscription.getSubscriptionType()).isEqualTo(findSubscriptionType);                        // :
    }
  }

}