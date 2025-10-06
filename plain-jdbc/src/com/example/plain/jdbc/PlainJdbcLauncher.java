package com.example.plain.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example")
public class PlainJdbcLauncher {
  public static void main(String[] args) {
    SpringApplication.run(PlainJdbcLauncher.class, args);
  }
}
