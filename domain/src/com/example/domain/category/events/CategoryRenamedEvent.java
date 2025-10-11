package com.example.domain.category.events;

import com.example.domain.category.CategoryId;
import com.example.domain.category.CategoryName;

import java.time.Instant;
import java.util.UUID;

public record CategoryRenamedEvent(
  UUID id,
  Instant createdAt,
  Long categoryId,
  String categoryName
) implements CategoryEvent {
  public static CategoryRenamedEvent create(CategoryId categoryId, CategoryName categoryName) {
    return new CategoryRenamedEvent(UUID.randomUUID(), Instant.now(), categoryId.value(), categoryName.value());
  }

  @Override
  public Long domainId() {
    return categoryId;
  }
}
