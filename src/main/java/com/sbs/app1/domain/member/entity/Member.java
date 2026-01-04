package com.sbs.app1.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Member {
  private static long lastId;
  private long id;
  private String username;
  private String password;

  static {
    lastId  = 0;
  }

  public Member(String username, String password) {
    this(++lastId, username, password);
  }
}
