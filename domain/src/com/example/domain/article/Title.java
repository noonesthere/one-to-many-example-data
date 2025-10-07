package com.example.domain.article;

import java.util.Objects;

public record Title(String value) {

  public Title {
    if (Objects.isNull(value) || value.isBlank()) {
      throw new IllegalStateException("Title should be specified");
    }

    if (value.length() > 100) {
      throw new IllegalStateException("Title length overflow ");
    }

  }

  public String asStringValue() {
    return value;
  }
}
