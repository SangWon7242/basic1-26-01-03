package com.sbs.app1.domain.home.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController // @Controller + @ResponseBody
public class HomeController {
  public int val = -1;

  @GetMapping("/")
  public String main() {
    return "Hello!";
  }

  @GetMapping("/home/main1")
  public String home1() {
    return "안녕하세요.";
  }

  @GetMapping("/home/main2")
  public String home2() {
    return "반갑습니다.";
  }

  @GetMapping("/home/increase")
  public int showIncrease() {
    return ++val;
  }

  @GetMapping("/home/decrease")
  public int showDecrease() {
    return --val;
  }

  @GetMapping("/home/initNumber")
  public String initNumber() {
    val = -1;
    return "값을 초기화 합니다.";
  }

  @GetMapping("/home/plus")
  // 스프링부트는 쿼리스트링의 파라미터를 함수 매개변수로 받을 수 있다.
  // @RequestParam 생략이 가능하다.
  // 파라미터의 기본값이 있는 경우 @RequestParam 생략이 불가능하다.
  // 기본값은 무조건 문자열이다.
  // 숫자 모양의 문자열은 스프링부트가 알아서 형변환 해서 처리해준다.
  public int showPlus(int a, @RequestParam(defaultValue = "0") int b) {
    return a + b;
  }

  // /home/plus2/1/2
  @GetMapping("/home/plus2/{a}/{b}")
  public int showPlus2(@PathVariable int a, @PathVariable int b) {
    return a + b;
  }

  @GetMapping("/home/returnBoolean")
  public boolean showReturnBoolean() {
    return true;
  }

  @GetMapping("/home/returnDouble")
  public double showReturnDouble() {
    return Math.PI;
  }

  @GetMapping("/home/returnArray")
  public int[] showReturnArray() {
    int[] arr = {10, 20, 30, 40, 50};
    return arr;
  }

  @GetMapping("/home/returnList")
  public List<Integer> showReturnList() {
    // List<Integer> arr = List.of(10, 20, 30, 40, 50);

    /*
    List<Integer> arr = new ArrayList<>() {{
      add(10);
      add(20);
      add(30);
      add(40);
      add(50);
    }};
    */
    List<Integer> arr = new ArrayList<>();
    arr.add(10);
    arr.add(20);
    arr.add(30);
    arr.add(40);
    arr.add(50);

    return arr;
  }

  @GetMapping("/home/returnMap")
  public  Map<String, Object> showReturnMap() {
    /*
    Map<String, Object> map = Map.of(
        "name", "홍길동",
        "age", 30,
        "address", "평택시",
        "isMarried", true,
        "phone", "010-1234-5678",
        "email", "user1@test.com",
        "hobbies", List.of("수영", "테니스", "축구")
    );
    */

    Map<String, Object> map = new LinkedHashMap<>() {{
      put("name", "홍길동");
      put("age", 30);
      put("address", "평택시");
      put("isMarried", true);
      put("phone", "010-1234-5678");
      put( "email", "user1@test.com");
      put("hobbies", List.of("수영", "테니스", "축구"));
    }};

    return map;
  }
}
