package com.sbs.app1.domain.member.controller;

import com.sbs.app1.domain.member.entity.Member;
import com.sbs.app1.domain.member.service.MemberService;
import com.sbs.app1.global.base.rq.Rq;
import com.sbs.app1.global.base.rsData.RsData;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;
  private final Rq rq;

  @GetMapping("/login")
  public String login() {
    return "member/login";
  }

  @ResponseBody
  @PostMapping("/login")
  public RsData login(String username, String password) {

    if (username == null || username.trim().isEmpty()) {
      return RsData.of("F-3", "아이디를 입력해주세요.");
    }

    if (password == null || password.trim().isEmpty()) {
      return RsData.of("F-4", "비밀번호를 입력해주세요.");
    }

    RsData rsData = memberService.tryLogin(username, password);

    if (rsData.isSuccess()) {
      Member member = (Member) rsData.getData();
      rq.setSession("loginedMemberId", member.getId());
    }

    return rsData;
  }

  @GetMapping("/logout")
  public RsData logout() {
    boolean cookieRemoved = rq.removeSession("loginedMemberId");

    if(!cookieRemoved) {
      return RsData.of("F-1", "로그아웃에 실패했습니다. 이미 로그아웃 상태입니다.");
    }

    return RsData.of("S-1", "로그아웃 되었습니다.");
  }

  @GetMapping("/me")
  public RsData showMe() {
    long loginedMemberId = rq.getSessionAsLong("loginedMemberId", 0L);

    boolean isLogined = loginedMemberId > 0;

    if (!isLogined) {
      return RsData.of("F-1", "로그인 후 이용해주세요.");
    }

    Member member = memberService.findById(loginedMemberId);

    return RsData.of("S-1", "당신의 username(은)는 '%s' 입니다.".formatted(member.getUsername()));
  }

  @GetMapping("/session")
  public String showSession() {
    return rq.getSessionDebugInfo().replaceAll("\n", "<br>");
  }

  @GetMapping("/myaccount")
  public String showMyAccount(Model model) {
    long loginedMemberId = rq.getLoginedMember();

    Member member = memberService.findById(loginedMemberId);
    model.addAttribute("member", member);

    return "member/myaccount";
  }
}
