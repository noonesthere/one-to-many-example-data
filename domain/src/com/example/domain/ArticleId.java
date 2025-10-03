package com.example.domain;

public class ArticleId {

  private final String value;

  private ArticleId(String value) {
    this.value = value;
  }

  public String asString() {
    return value;
  }

  public static ArticleId from(String value) {
    // validation
    return new ArticleId(value);
  }
}
