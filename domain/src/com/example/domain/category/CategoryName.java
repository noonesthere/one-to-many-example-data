package com.example.domain.category;

import com.example.domain.category.errors.CategoryNameError;
import com.example.domain.category.errors.CategoryNameLengthOverflowError;
import io.vavr.control.Either;

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

  public static Either<CategoryNameError, CategoryName> fromStringValue(String value) {

    if (Objects.isNull(value) || value.isBlank()) {
      return Either.left(new CategoryNameError());
    }

    if (value.length() > CATEGORY_NAME_LENGTH) {
      return Either.left(new CategoryNameLengthOverflowError());
    }

    return Either.right(new CategoryName(value));
  }
}
