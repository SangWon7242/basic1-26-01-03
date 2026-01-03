package com.sbs.app1.domain.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // @Controller : 스프링부트한테 해당 클래스는 컨트롤러의 역할이라고 알려줌
public class HomeController {
  @GetMapping("/")
  @ResponseBody // 응답한 결과물을 body 에 보냄
  public String main() {
    return "Hello!";
  }

  @GetMapping("/home/main1")
  @ResponseBody
  public String home1() {
    return "안녕하세요.";
  }

  @GetMapping("/home/main2")
  @ResponseBody
  public String home2() {
    return "반갑습니다.";
  }
}
