package com.sbs.app1.domain.post.post.service;

import com.sbs.app1.domain.post.post.entity.Post;
import com.sbs.app1.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {
  private final PostRepository postRepository;

  public Post write(String title, String content) {
    /*
    Post post = Post.builder()
        .createDate(LocalDateTime.now())
        .modifyDate(LocalDateTime.now())
        .title(title)
        .content(content)
        .build();
     */

    Post post = Post.builder()
        .title(title)
        .content(content)
        .build();

    postRepository.save(post);

    return post;
  }
}
