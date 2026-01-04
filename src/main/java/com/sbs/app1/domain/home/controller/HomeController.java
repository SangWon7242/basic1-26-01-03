package com.sbs.app1.domain.home.controller;

import com.sbs.app1.domain.member.entity.Member;
import com.sbs.app1.domain.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController // @Controller + @ResponseBody
public class HomeController {
  public int val;
  public List<Person> personList;

  // 필드 주입
  @Autowired
  private MemberService memberService;

  public HomeController() {
    val = -1;
    personList = new ArrayList<>();
  }

  @GetMapping("/home/")
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
  public Member1 showReturnMember() {
    Member1 member = new Member1(
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
  public Member1 showReturnMember2() {
    Member1 member2 = new Member1(
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


  @GetMapping("/home/makePersonData")
  public String addPerson() {
    personList.add(new Person("홍길동", 11));
    personList.add(new Person("홍길순", 22));
    personList.add(new Person("임꺽정", 33));
    return "사람 테스트 데이터 생성!";
  }

  @GetMapping("/home/addPerson")
  public String addPerson(String name, int age) {
    Person p = new Person(name, age);

    personList.add(p);

    return "%d번 사람이 추가되었습니다.".formatted(p.getId());
  }

  @GetMapping("/home/removePerson")
  public String removePerson(int id) {
    // v1
    /*
    Person foundPerson = null;

    for(Person person : personList) {
      if(person.getId() == id) {
        foundPerson = person;
        break;
      }
    }

    if(foundPerson == null) return "%d번 사람은 존재하지 않습니다.".formatted(id);

    personList.remove(foundPerson);
    */

    // v2
    /*
    Person person = personList.stream()
        .filter(p -> p.getId() == id)
        .findFirst()
        .orElse(null);

    if(person == null) return "%d번 사람은 존재하지 않습니다.".formatted(id);

    personList.remove(person);
    */

    // v3
    // 삭제 성공시 true, 실패시 false를 반환
    boolean removed = personList.removeIf(p -> p.getId() == id);

    if (!removed) return "%d번 사람은 존재하지 않습니다.".formatted(id);

    return "%d번 사람이 삭제되었습니다.".formatted(id);
  }

  @GetMapping("/home/modifyPerson")
  public String modifyPerson(int id, String name, int age) {

    /*
    Person foundPerson = null;

    for(Person person : personList) {
      if(person.getId() == id) {
        foundPerson = person;
        break;
      }
    }

    if(foundPerson == null) return "%d번 사람은 존재하지 않습니다.".formatted(id);

    foundPerson.setName(name);
    foundPerson.setAge(age);
     */

    Person person = personList.stream()
        .filter(p -> p.getId() == id)
        .findFirst()
        .orElse(null);

    if (person == null) return "%d번 사람은 존재하지 않습니다.".formatted(id);

    person.setName(name);
    person.setAge(age);

    return "%d번 사람이 수정되었습니다.".formatted(id);
  }


  @GetMapping("/home/showPeople")
  public List<Person> showPeople() {
    return personList;
  }

  @GetMapping("/home/cookie/increase")
  public int showCookieIncrease(HttpServletRequest req, HttpServletResponse resp) {
    // HttpServletRequest : 받은 편지
    // HttpServletResponse : 보낼 편지
    
    // 최초의 한번은 쿠키가 존재하지 않는다.
    int countInCookie = 0;

    if(req.getCookies() != null) {
      // 쿠키 배열을 스트림으로 변환
      countInCookie = Arrays.stream(req.getCookies())
          .filter(cookie -> cookie.getName().equals("count")) // 쿠키 이름이 'count'인 쿠키만 필터림
          .map(Cookie::getValue) // 쿠키의 값을 가져옴
          .mapToInt(Integer::parseInt) // 가져온 쿠키값을 정수값으로 형변환
          .findFirst() // 필터링 된 쿠키 중에 첫 번째 쿠키를 가져옴
          .orElse(0); // 만약 찾지 못한 경우 기본값 0을 반환
    }

    int newCountInCookie = countInCookie + 1;

    resp.addCookie(new Cookie("count", newCountInCookie + ""));

    return newCountInCookie;
  }

  @GetMapping("/home/reqAndResp")
  public void showReqAndResp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    int age = Integer.parseInt(req.getParameter("age"));
    resp.getWriter().append("Hello, I'm %d years old.".formatted(age));
  }

  @GetMapping("/home/user1")
  public Member showUser1() {
    return memberService.findByUsername("user1");
  }
}

class Member1 {
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

  public Member1(String name, int age, String address, boolean isMarried, String phone, String email, List<String> hobbies) {
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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
class Person {
  private static int lastId;
  private int id;
  private String name;
  private int age;

  static {
    lastId = 0;
  }

  public Person(String name, int age) {
    this(++lastId, name, age);
  }
}
