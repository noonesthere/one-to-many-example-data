package com.example.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example")
@EnableJpaRepositories(basePackages = "com.example.data.jpa.persistence")
@EntityScan(basePackages = "com.example.data.jpa.persistence")
public class JPALauncher {

  public static void main(String[] args) {
    SpringApplication.run(JPALauncher.class, args);
  }
}
