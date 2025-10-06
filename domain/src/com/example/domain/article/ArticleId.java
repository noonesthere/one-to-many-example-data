package com.example.domain.article;

public class ArticleId {

  private final Long value;

  private ArticleId(Long value) {
    this.value = value;
  }

  public Long asLong() {
    return value;
  }

  public static ArticleId from(Long value) {
    // validation
    return new ArticleId(value);
  }
}
