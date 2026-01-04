package com.sbs.app1.domain.home.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
  public Map<String, Object> showReturnMap() {
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
      put("email", "user1@test.com");
      put("hobbies", List.of("수영", "테니스", "축구"));
    }};

    return map;
  }

  @GetMapping("/home/returnMember")
  public Member showReturnMember() {
    Member member = new Member(
        "홍길동",
        30,
        "평택시",
        true,
        "010-1234-5678",
        "user1@test.com",
        List.of("수영", "테니스", "축구")
    );

    return member;
  }

  @GetMapping("/home/returnMember2")
  public Member showReturnMember2() {
    Member member2 = new Member(
        "홍길동",
        30,
        "평택시",
        true,
        "010-1234-5678",
        "user1@test.com",
        List.of("수영", "테니스", "축구")
    );

    return member2;
  }

  @GetMapping("/home/returnMemberList")
  public List<Map<String, Object>> showReturnMemberList() {
    Map<String, Object> member1 = new LinkedHashMap<>() {{
      put("name", "홍길동");
      put("age", 30);
      put("address", "평택시");
      put("isMarried", true);
      put("phone", "010-1234-5678");
      put("email", "user1@test.com");
      put("hobbies", List.of("수영", "테니스", "축구"));
    }};

    Map<String, Object> member2 = new LinkedHashMap<>() {{
      put("name", "홍길순");
      put("age", 32);
      put("address", "군포시");
      put("isMarried", false);
      put("phone", "010-1234-5678");
      put("email", "user2@test.com");
      put("hobbies", List.of("런닝", "헬스", "배구"));
    }};

    Map<String, Object> member3 = new LinkedHashMap<>() {{
      put("name", "임꺽정");
      put("age", 27);
      put("address", "안산시");
      put("isMarried", false);
      put("phone", "010-1234-5678");
      put("email", "user3@test.com");
      put("hobbies", List.of("배구", "야구", "럭비"));
    }};

    List<Map<String, Object>> memberList = new ArrayList<>();
    memberList.add(member1);
    memberList.add(member2);
    memberList.add(member3);

    return memberList;
  }

  @GetMapping("/home/returnMemberList2")
  public List<Member2> showReturnMemberList2() {
    List<Member2> memberList = new ArrayList<>();
    memberList.add(
        new Member2("홍길동", 30,
            "평택시", true,
            "010-1234-5678", "user1@test.com",
            List.of("수영", "테니스", "축구")));

    memberList.add(
        new Member2("홍길순", 32,
            "안산시", false,
            "010-1234-5678", "user3@test.com",
            List.of("런닝", "헬스", "배구")));

    memberList.add(
        new Member2("임꺽정", 27,
            "평택시", true,
            "010-1234-5678", "user1@test.com",
            List.of("배구", "야구", "럭비")));

    return memberList;
  }
}

class Member {
  private String name;
  private int age;
  private String address;
  private boolean isMarried;
  private String phone;
  private String email;
  private List<String> hobbies;

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public String getAddress() {
    return address;
  }

  public boolean isMarried() {
    return isMarried;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public List<String> getHobbies() {
    return hobbies;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setMarried(boolean married) {
    isMarried = married;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setHobbies(List<String> hobbies) {
    this.hobbies = hobbies;
  }

  public Member(String name, int age, String address, boolean isMarried, String phone, String email, List<String> hobbies) {
    this.name = name;
    this.age = age;
    this.address = address;
    this.isMarried = isMarried;
    this.phone = phone;
    this.email = email;
    this.hobbies = hobbies;
  }

  @Override
  public String toString() {
    return "Member{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", address='" + address + '\'' +
        ", isMarried=" + isMarried +
        ", phone='" + phone + '\'' +
        ", email='" + email + '\'' +
        ", hobbies=" + hobbies +
        '}';
  }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Member2 {
  private String name;
  private int age;
  private String address;
  private boolean isMarried;
  private String phone;
  private String email;
  private List<String> hobbies;
}