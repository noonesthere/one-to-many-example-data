package com.example.domain.category;

import java.util.Objects;

public record CategoryName(String value) {

  private static final int CATEGORY_NAME_LENGTH = 100;

  public CategoryName {
    if (Objects.isNull(value) || value.isBlank()) {
      throw new IllegalArgumentException();
    }

    if (value.length() > CATEGORY_NAME_LENGTH) {
      throw new IllegalArgumentException();
    }
  }

  boolean same(CategoryName name) {
    return this.value.equals(name.value());
  }
}
