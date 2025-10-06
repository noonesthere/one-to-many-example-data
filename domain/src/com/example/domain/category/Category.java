package com.example.domain.category;

import com.example.common.types.DomainEntity;
import com.example.common.types.Version;
import com.example.domain.category.commands.CreateCategoryCommand;
import com.example.domain.category.events.CreatedCategoryEvent;
import jakarta.annotation.Nullable;

import java.time.Instant;

public class Category extends DomainEntity<CategoryId> {

  public final CategoryName name;
  @Nullable private final  Instant updatedAt;
  @Nullable private final  Instant deletedAt;

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
    Category category = new Category(
      command.categoryId(),
      Version.newVersion(),
      command.categoryName(),
      null,
      null
    );
    category.addEvent(
      new CreatedCategoryEvent(
        category.id.asLongValue(),
        category.name.asStringValue()
      )
    );

    return category;
  }

  public Instant updatedAt() {
    return updatedAt;
  }

  public Instant deletedAt(){
    return deletedAt;
  }
}
