package com.sbs.app1.global.initData;

import com.sbs.app1.domain.member.service.MemberService;
import com.sbs.app1.domain.post.post.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev", "test"})
// dev : 개발 모드
// test : 테스트 모드
public class NotProd {
  @Bean // 빈 등록
  CommandLineRunner initData(MemberService memberService, PostService postService) {
    return args -> {
      memberService.tryJoin("user1", "1234", "유저1");
      memberService.tryJoin("user2", "1234", "유저2");
      memberService.tryJoin("user3", "1234", "유저3");

      postService.write("제목1", "내용1");
      postService.write("제목2", "내용2");
    };
  }
}
