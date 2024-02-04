package com.jbjeon.chat.entity;

public enum SubscriptionType {
  NONE("구독 전"), MONTHLY("한달 정기 구독"), ANNUAL("일년 정기 구독");

  private String description;

  SubscriptionType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return this.description;
  }
}
