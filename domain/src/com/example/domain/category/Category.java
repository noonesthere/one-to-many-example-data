package com.example.domain.category;

import com.example.common.types.DomainEntity;
import com.example.common.types.DomainEvent;
import com.example.common.types.Version;
import com.example.domain.category.commands.CreateCategoryCommand;
import com.example.domain.category.commands.RenameCategoryCommand;
import com.example.domain.category.events.CategoryCreatedEvent;
import com.example.domain.category.events.CategoryRenamedEvent;
import jakarta.annotation.Nullable;

import java.time.Instant;
import java.util.List;

public class Category extends DomainEntity<CategoryId> {

  private CategoryId id;
  private CategoryName name;
  private Version version;
  @Nullable
  private Instant deletedAt;

  public Category(
    CategoryId categoryId,
    CategoryName name,
    Version version,
    @Nullable Instant deletedAt
  ) {
    this.id = categoryId;
    this.name = name;
    this.version = version;
    this.deletedAt = deletedAt;
  }

  private Category() {
  }

  public static Category rebuild(List<DomainEvent> events) {
    final var category = new Category();

    events.forEach(event -> {
      switch (event) {
        case CategoryCreatedEvent e -> category.onEvent(e);
        case CategoryRenamedEvent e -> category.onEvent(e);
        default -> throw new IllegalStateException("Unexpected value: " + event);
      }
    });

    return category;
  }

  /**
   * Command handlers
   */

  public static Category handle(CreateCategoryCommand command) {
    // some business logic maybe

    final var event = CategoryCreatedEvent.create(
      command.categoryId(),
      command.categoryName()
    );

    final var category = new Category();
    category.onEvent(event);
    category.addEvent(event);
    return category;
  }

  public void handle(RenameCategoryCommand command) {
    // business logic: graceful idempotent operation
    if (!this.name().same(command.name())) {
      final var event = CategoryRenamedEvent.create(id, command.name(), this.version.next());
      onEvent(event);
      addEvent(event);
    }
  }

  /**
   *
   * Event handlers
   */

  private void onEvent(CategoryCreatedEvent event) {
    this.id = new CategoryId(event.categoryId());
    this.name = new CategoryName(event.categoryName());
    this.version = Version.newVersion();
    this.deletedAt = null;
  }

  private void onEvent(CategoryRenamedEvent e) {
    this.name = new CategoryName(e.categoryName());
  }

  /**
   * Getters and setters
   */

  @Nullable
  public Instant deletedAt() {
    return deletedAt;
  }

  public CategoryName name() {
    return name;
  }

  public Version version() {
    return version;
  }

  public CategoryId id() {
    return id;
  }
}
