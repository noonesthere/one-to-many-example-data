package com.example.domain.category;

import java.util.Objects;

public record CategoryId(Long value) {
  public CategoryId {
    if (Objects.isNull(value)) {
      throw new IllegalArgumentException();
    }
  }
}
