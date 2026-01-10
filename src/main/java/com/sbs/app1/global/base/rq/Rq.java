package com.sbs.app1.global.base.rq;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public class Rq {
  private final HttpServletRequest req;
  private final HttpServletResponse resp;

  public void setCookie(String name, long value) {
     setCookie(name, value + "");
  }

  private void setCookie(String name, String value) {
    resp.addCookie(new Cookie(name, value));
  }

  public long getCookieAsLong(String name, long defaultValue) {
    String value = getCookie(name, null);

    if(value == null) return defaultValue;

    try {
      return Long.parseLong(value);
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  private String getCookie(String name, String defaultValue) {
    if(req.getCookies() == null) return defaultValue;

    return Arrays.stream(req.getCookies())
        .filter(cookie -> cookie.getName().equals("loginedMemberId"))
        .map(Cookie::getValue)
        .findFirst()
        .orElse(defaultValue);
  }

  public boolean removeCookie(String name) {
    if(req.getCookies() != null) {
      Arrays.stream(req.getCookies())
          .filter(cookie -> cookie.getName().equals("loginedMemberId"))
          .forEach(cookie -> {
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
          });
    }

    // anyMatch : 조건을 만족하면 true, 조건이 일치하지 않으면 false
    return Arrays.stream(req.getCookies()).anyMatch(cookie -> cookie.getName().equals(name));
  }
}
