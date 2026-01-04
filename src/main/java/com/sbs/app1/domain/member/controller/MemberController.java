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
    return memberService.tryLogin(username, password);
  }
}
