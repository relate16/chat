package com.jbjeon.chat.repository;

import com.jbjeon.chat.entity.Subscription;
import com.jbjeon.chat.entity.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
  List<Subscription> findBySubscriptionType(SubscriptionType subscriptionType);
}
