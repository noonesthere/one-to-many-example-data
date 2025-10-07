package com.example.domain.article;

import java.util.Objects;

public enum ArticleStatus {
  CREATED(1),
  DELETED(2),
  PUBLISHED(3),
  ;

  public final int id;

  ArticleStatus(int id) {
    this.id = id;
  }

  public static ArticleStatus from(Integer status) {
    if (Objects.isNull(status)) {
      //WARNING
      return CREATED;
    }

    return switch (status) {
      case 1 -> CREATED;
      case 2 -> DELETED;
      case 3 -> PUBLISHED;
      default -> CREATED;
    };

  }
}
