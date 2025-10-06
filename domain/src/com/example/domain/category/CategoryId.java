package com.example.domain.category;

import java.util.Objects;

public class CategoryId {
  private final Long value;

  private CategoryId(Long value) {
    this.value = value;
  }

  public Long asLongValue() {
    return value;
  }

  public static CategoryId from(Long value) {
    if (Objects.isNull(value)) {
      throw new IllegalArgumentException();
    }

    return new CategoryId(value);
  }
}
