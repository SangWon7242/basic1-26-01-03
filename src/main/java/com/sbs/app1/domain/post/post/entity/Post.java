package com.sbs.app1.domain.post.post.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class) // Auditing 활성화
public class Post {
  @Id // PK : primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
  private long id;

  @CreatedDate // 최초 생성 시점에 자동으로 값이 설정
  private LocalDateTime createDate;

  @LastModifiedBy // 최초 수정 시점에서 자동으로 값이 설정
  private LocalDateTime modifyDate;
  private String title;
  private String content;
}
