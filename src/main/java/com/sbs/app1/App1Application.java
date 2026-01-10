package com.sbs.app1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // @EnableJpaAuditing : JPA Auditing 활성화
public class App1Application {

  public static void main(String[] args) {
    SpringApplication.run(App1Application.class, args);
  }
}
