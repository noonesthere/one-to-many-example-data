package com.example.domain.category.events;

import com.example.common.types.DomainEvent;
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
    return new CategoryRenamedEvent(UUID.randomUUID(), Instant.now(), categoryId.asLongValue(), categoryName.asStringValue());
  }
}
