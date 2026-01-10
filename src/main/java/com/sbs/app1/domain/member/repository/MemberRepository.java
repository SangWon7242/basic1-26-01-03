package com.sbs.app1.domain.member.repository;

import com.sbs.app1.domain.member.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository {
  private List<Member> members;

  public MemberRepository() {
    members = new ArrayList<>();

    for (int i = 1; i <= 10; i++) {
      String username = "user" + i;      // 예: user1, user2 ...
      String password = "1234" + i;      // 예: 12341, 12342 ...

      members.add(new Member(username, password));
    }
  }

  public Member findByUsername(String username) {
    return members.stream()
        .filter(member -> member.getUsername().equals(username))
        .findFirst()
        .orElse(null);
  }

  public Member findById(long id) {
    return members.stream()
        .filter(member -> member.getId() == id)
        .findFirst()
        .orElse(null);
  }
}