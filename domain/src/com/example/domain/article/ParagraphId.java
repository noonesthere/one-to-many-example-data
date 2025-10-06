package com.example.domain.article;

public record ParagraphId(Long value) {
  public Long asLongValue() {
    return value;
  }
}
