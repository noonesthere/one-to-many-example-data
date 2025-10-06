package com.example.data.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.example")
public class JdbcDataLauncher {
  public static void main(String[] args) {
    SpringApplication.run(JdbcDataLauncher.class, args);
  }
}
