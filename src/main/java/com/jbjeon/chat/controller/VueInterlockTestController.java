package com.jbjeon.chat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proxy")
public class VueInterlockTestController {
  @GetMapping("/hello")
  public String getHello() {
    return "hello";
  }
}
