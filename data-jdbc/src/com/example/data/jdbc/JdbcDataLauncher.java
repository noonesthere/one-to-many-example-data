package com.example.data.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.example")
@SpringBootApplication
public class JdbcDataLauncher {
  public static void main(String[] args) {
    SpringApplication.run(JdbcDataLauncher.class, args);
  }
}
