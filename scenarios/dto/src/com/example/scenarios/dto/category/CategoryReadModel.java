package com.example.scenarios.dto.category;

import com.example.domain.category.Category;
import com.example.domain.category.CategoryCounter;
import com.example.domain.category.CategoryId;
import com.example.domain.category.CategoryName;
import jakarta.annotation.Nullable;

import java.time.Instant;

public record CategoryReadModel(
  CategoryId id,
  CategoryName name,
  @Nullable Instant updatedAt,
  int counter
) {
  public static CategoryReadModel from(Category category, CategoryCounter counter) {
    return new CategoryReadModel(
      category.id,
      category.name(),
      category.updatedAt(),
      counter.value()
    );
  }
}
