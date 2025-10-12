package com.example.scenarios.dto.category;

import com.example.domain.category.Category;
import com.example.domain.category.CategoryCounter;
import com.example.domain.category.CategoryId;
import com.example.domain.category.CategoryName;

public record CategoryReadModel(
  CategoryId id,
  CategoryName name,
  int counter
) {
  public static CategoryReadModel from(Category category, CategoryCounter counter) {
    return new CategoryReadModel(
      category.id,
      category.name(),
      counter.value()
    );
  }
}
