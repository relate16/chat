package com.jbjeon.chat.entity;

public enum Gender {
  M("남자"), F("여자"), O("기타");

  private String description;

  Gender(String description) {
    this.description = description;
  }

  public String getDescription() {
    return this.description;
  }
}
