package com.sbs.app1.domain.post.post.controller;

import com.sbs.app1.domain.post.post.entity.Post;
import com.sbs.app1.domain.post.post.service.PostService;
import com.sbs.app1.global.base.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
  private final PostService postService;

  @GetMapping("/write")
  @ResponseBody
  public RsData write(String title, String content) {
    Post post = postService.write(title, content);

    return RsData.of("S-1", "%d번 게시물이 등록되었습니다.".formatted(post.getId()), post);
  }
}
