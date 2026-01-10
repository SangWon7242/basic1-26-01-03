package com.sbs.app1.domain.post.post.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Post {
  @Id // PK : primary key
  private long id;
  private String title;
  private String content;
}
