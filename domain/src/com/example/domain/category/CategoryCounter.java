package com.example.domain.category;

public record CategoryCounter(CategoryId categoryId, Integer value) {

  public CategoryCounter increase() {
    return new CategoryCounter(categoryId, value + 1);
  }

  public static CategoryCounter newCounter(CategoryId categoryId) {
    return new CategoryCounter(categoryId, 0);
  }
}
