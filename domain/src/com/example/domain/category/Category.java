package com.example.domain.category;

import com.example.common.types.DomainEntity;
import com.example.common.types.Version;
import com.example.domain.category.commands.CreateCategoryCommand;
import com.example.domain.category.commands.RenameCategoryCommand;
import com.example.domain.category.events.CategoryCreatedEvent;
import com.example.domain.category.events.CategoryRenamedEvent;
import jakarta.annotation.Nullable;

import java.time.Instant;

public class Category extends DomainEntity<CategoryId> {

  private CategoryName name;
  @Nullable
  private final Instant deletedAt;

  public Category(
    CategoryId categoryId,
    Version version,
    CategoryName name,
    @Nullable Instant deletedAt
  ) {
    super(categoryId, version);
    this.name = name;
    this.deletedAt = deletedAt;
  }

  public static Category create(CreateCategoryCommand command) {

    final var category = new Category(
      command.categoryId(),
      Version.newVersion(),
      command.categoryName(),
      null
    );
    category.addEvent(CategoryCreatedEvent.create(category.id, category.name));
    return category;
  }

  public Category rename(RenameCategoryCommand command) {
    this.name = command.name();
    addEvent(CategoryRenamedEvent.create(id, name, version().value()));
    return this;
  }

  public Instant deletedAt() {
    return deletedAt;
  }

  public CategoryName name() {
    return name;
  }

  @Override
  protected void update() {
    // stub used for version with updatedAt field
  }
}
