package com.example.domain.category;

import java.util.Objects;

public class CategoryName {

  private static final int CATEGORY_NAME_LENGTH = 100;

  private final String value;

  private CategoryName(String value) {
    this.value = value;
  }

  public String asStringValue() {
    return value;
  }

  public static CategoryName from(String value) {

    if (Objects.isNull(value) || value.isBlank()) {
      throw new IllegalArgumentException();
    }

    if (value.length() > CATEGORY_NAME_LENGTH) {
      throw new IllegalArgumentException();
    }

    return new CategoryName(value);
  }
}
