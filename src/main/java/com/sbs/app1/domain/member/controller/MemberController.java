package com.sbs.app1.domain.member.controller;

import com.sbs.app1.domain.member.entity.Member;
import com.sbs.app1.domain.member.service.MemberService;
import com.sbs.app1.global.base.rsData.RsData;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/member")
@AllArgsConstructor
public class MemberController {
  private final MemberService memberService;
  
  // 생성자 주입
  /*
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }
  */

  @GetMapping("/login")
  public RsData login(String username, String password, HttpServletResponse resp) {
    if(username == null || username.trim().isEmpty()) {
      return RsData.of("F-3", "아이디를 입력해주세요.");
    }

    if(password == null || password.trim().isEmpty()) {
      return RsData.of("F-4", "비밀번호를 입력해주세요.");
    }

    RsData rsData = memberService.tryLogin(username, password);

    if(rsData.isSuccess()) {
      long memberId = (long) rsData.getData();
      resp.addCookie(new Cookie("loginedMemberId", memberId + ""));
    }

    return rsData;
  }

  @GetMapping("/logout")
  public RsData login(HttpServletRequest req, HttpServletResponse resp) {
    if(req.getCookies() != null) {
      Arrays.stream(req.getCookies())
          .filter(cookie -> cookie.getName().equals("loginedMemberId"))
          .forEach(cookie -> {
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
          });
    }

    return RsData.of("S-1", "로그아웃 되었습니다.");
  }

  @GetMapping("/me")
  public RsData showMe(HttpServletRequest req) {
    long loginedMemberId = 0;

    if(req.getCookies() != null) {
      loginedMemberId = Arrays.stream(req.getCookies())
          .filter(cookie -> cookie.getName().equals("loginedMemberId"))
          .map(Cookie::getValue)
          .mapToLong(Long::parseLong)
          .findFirst()
          .orElse(0);
    }

    boolean isLogined = loginedMemberId > 0;

    if(!isLogined) {
      return RsData.of("F-1", "로그인 후 이용해주세요.");
    }

    Member member = memberService.findById(loginedMemberId);

    return RsData.of("S-1", "당신의 username(은)는 '%s' 입니다.".formatted(member.getUsername()));
  }
}
