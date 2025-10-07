package com.example.domain.category.events;

import com.example.domain.category.CategoryId;
import com.example.domain.category.CategoryName;

import java.time.Instant;
import java.util.UUID;

public record CategoryCreatedEvent(
  UUID id,
  Instant createdAt,
  Long categoryId,
  String categoryName
) implements CategoryEvent {

  public static CategoryCreatedEvent create(CategoryId categoryId, CategoryName categoryName) {
    return new CategoryCreatedEvent(UUID.randomUUID(), Instant.now(), categoryId.asLongValue(), categoryName.asStringValue());
  }
}
