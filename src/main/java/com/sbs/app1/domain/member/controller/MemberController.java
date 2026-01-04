package com.sbs.app1.domain.member.controller;

import com.sbs.app1.domain.member.service.MemberService;
import com.sbs.app1.global.base.rsData.RsData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
  private MemberService memberService;

  public MemberController() {
    memberService = new MemberService();
  }

  @GetMapping("/login")
  public RsData login(String username, String password) {
    if(username == null || username.trim().isEmpty()) {
      return RsData.of("F-3", "아이디를 입력해주세요.");
    }

    if(password == null || password.trim().isEmpty()) {
      return RsData.of("F-4", "비밀번호를 입력해주세요.");
    }

    return memberService.tryLogin(username, password);
  }
}
