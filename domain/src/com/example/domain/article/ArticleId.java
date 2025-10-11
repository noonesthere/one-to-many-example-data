package com.example.domain.article;

import java.util.Objects;

public record ArticleId(Long value) {
  public ArticleId {
    Objects.requireNonNull(value, "Invariant: value must not be null");
  }
}
