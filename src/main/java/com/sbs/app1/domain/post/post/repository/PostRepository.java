package com.sbs.app1.domain.post.post.repository;

import com.sbs.app1.domain.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// <Post, Long> : <엔티티이름, 엔티티 id 타입>
public interface PostRepository extends JpaRepository<Post, Long> {
}
