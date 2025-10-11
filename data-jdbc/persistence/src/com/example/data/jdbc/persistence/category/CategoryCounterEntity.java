package com.example.data.jdbc.persistence.category;

import com.example.domain.category.CategoryCounter;
import com.example.domain.category.CategoryId;

public record CategoryCounterEntity(
  Long categoryId,
  Integer value
) {

  CategoryCounter to() {
    return new CategoryCounter(new CategoryId(categoryId), value);
  }

  static CategoryCounterEntity from(CategoryCounter categoryCounter) {
    return new CategoryCounterEntity(categoryCounter.categoryId().value(), categoryCounter.value());
  }
}
