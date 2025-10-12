package com.example.domain.category.events;

import com.example.domain.category.CategoryId;
import com.example.domain.category.CategoryName;

import java.time.Instant;
import java.util.UUID;

public record CategoryRenamedEvent(
  UUID id,
  Instant createdAt,
  Long categoryId,
  String categoryName,
  Long previousVersion
) implements CategoryEvent {
  public static CategoryRenamedEvent create(CategoryId categoryId, CategoryName categoryName, Long previousVersion) {
    return new CategoryRenamedEvent(
      UUID.randomUUID(),
      Instant.now(),
      categoryId.value(),
      categoryName.value(),
      previousVersion
    );
  }

  @Override
  public Long domainId() {
    return categoryId;
  }
}
