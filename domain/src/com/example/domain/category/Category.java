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
import java.util.ArrayList;
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
    final var domainEvents = new ArrayList<>(events); // smell

    final CategoryCreatedEvent createEvent = (CategoryCreatedEvent) domainEvents.removeFirst(); // smell

    final Category category = handleInitialEvent(createEvent);
    domainEvents.forEach(category::applyEvent);

    return category;
  }

  private static Category handleInitialEvent(CategoryCreatedEvent event) {

    final Long id = event.categoryId();
    final String name = event.categoryName();
    final Version version = Version.newVersion();

    final Category category = new Category(
      new CategoryId(id),
      version,
      new CategoryName(name),
      null
    );

    category.updateVersion();
    return category;
  }

  private void applyEvent(DomainEvent event) {
    switch (event) {
      case CategoryRenamedEvent e -> {
        this.setName(e);
      }
      default -> throw new IllegalStateException("Unexpected value: " + event);
    }

  }

  private void setName(CategoryRenamedEvent e) {
    this.name = new CategoryName(e.categoryName());
    this.setVersion(Version.from(e.version()));
  }

  public static Category create(CreateCategoryCommand command) {
    final var event = CategoryCreatedEvent.create(
      command.categoryId(),
      command.categoryName()
    );

    final var category = handleInitialEvent(event);
    category.addEvent(event);

    return category;
  }

  public void rename(RenameCategoryCommand command) {
    updateVersion();
    final var event = CategoryRenamedEvent.create(id, command.name(), version());
    applyEvent(event);
    addEvent(event);
  }

  public Instant deletedAt() {
    return deletedAt;
  }

  public CategoryName name() {
    return name;
  }
}
