package com.example.domain.category;

import com.example.common.types.DomainEntity;
import com.example.common.types.Version;
import com.example.domain.category.commands.CreateCategoryCommand;
import com.example.domain.category.events.CategoryCreatedEvent;
import jakarta.annotation.Nullable;

import java.time.Instant;

public class Category extends DomainEntity<CategoryId> {

  public final CategoryName name;
  @Nullable
  private final Instant updatedAt;
  @Nullable
  private final Instant deletedAt;

  public Category(
    CategoryId categoryId,
    Version version,
    CategoryName name,
    @Nullable Instant updatedAt,
    @Nullable Instant deletedAt
  ) {
    super(categoryId, version);
    this.name = name;
    this.updatedAt = updatedAt;
    this.deletedAt = deletedAt;
  }

  public static Category create(CreateCategoryCommand command) {
    final var category = new Category(
      command.categoryId(),
      Version.newVersion(),
      command.categoryName(),
      null,
      null
    );
    category.addEvent(CategoryCreatedEvent.create(category.id, category.name));
    return category;
  }

  public Instant updatedAt() {
    return updatedAt;
  }

  public Instant deletedAt() {
    return deletedAt;
  }
}
