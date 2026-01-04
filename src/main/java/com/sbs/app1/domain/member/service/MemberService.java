package com.sbs.app1.domain.member.service;

import com.sbs.app1.domain.member.entity.Member;
import com.sbs.app1.domain.member.repository.MemberRepository;
import com.sbs.app1.global.base.rsData.RsData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

// @Component :아래 클래스는 Ioc 컨테이너에 의해 생사소멸이 관리된다.
@Service
@AllArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;

  /*
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }
  */

  public RsData tryLogin(String username, String password) {
    Member member = memberRepository.findByUsername(username);

    if(member == null) {
      return RsData.of("F-2", "%s(은)는 존재하지 않는 회원입니다.".formatted(username));
    }

    if(!member.getPassword().equals(password)) {
      return RsData.of("F-1", "비밀번호가 일치하지 않습니다.");
    }

    return RsData.of("S-1", "'%s'님 환영합니다.".formatted(username));
  }

  public Member findByUsername(String username) {
    return memberRepository.findByUsername(username);
  }
}
