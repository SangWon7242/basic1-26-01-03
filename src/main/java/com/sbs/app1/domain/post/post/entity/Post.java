package com.sbs.app1.domain.post.post.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Post {
  @Id // PK : primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
  private long id;
  private String title;
  private String content;
}
