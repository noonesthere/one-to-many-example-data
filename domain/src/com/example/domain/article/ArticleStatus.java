package com.example.domain.article;

public enum ArticleStatus {
  CREATED(1),
  DELETED(2),
  PUBLISHED(3),
  ;

  private final int id;

  ArticleStatus(int id) {
    this.id = id;
  }
}
