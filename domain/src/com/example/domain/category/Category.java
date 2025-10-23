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

  public static Category rebuild(List<DomainEvent> events) {
    final CategoryCreatedEvent createEvent = (CategoryCreatedEvent) events.removeFirst();
    final Category category = initialize(createEvent);
    events.forEach(category::applyEvent);
    return category;
  }

  private static Category initialize(CategoryCreatedEvent event) {
    final Long id = event.categoryId();
    final String name = event.categoryName();

    return new Category(
      new CategoryId(id),
      Version.newVersion(),
      new CategoryName(name),
      null
    );
  }

  private Category applyEvent(DomainEvent event) {
    return switch (event) {
      case CategoryRenamedEvent e -> {
        this.name = new CategoryName(e.categoryName());
        yield this;
      }
      default -> throw new IllegalStateException("Unexpected value: " + event);
    };
  }

  public static Category create(CreateCategoryCommand command) {
    final var event = CategoryCreatedEvent.create(
      command.categoryId(),
      command.categoryName()
    );

    final var category = initialize(event);
    category.addEvent(event);
    return category;
  }

  public Category rename(RenameCategoryCommand command) {
    final var event = CategoryRenamedEvent.create(id, command.name(), version().value());
    applyEvent(event);
    addEvent(event);
    return this;
  }

  public Instant deletedAt() {
    return deletedAt;
  }

  public CategoryName name() {
    return name;
  }
}
