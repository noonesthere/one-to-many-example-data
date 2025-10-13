package com.example.plain.jdbc.persistence;

import com.example.common.types.Version;
import com.example.common.utilities.CollectionsUtils;
import com.example.domain.category.Category;
import com.example.domain.category.CategoryId;
import com.example.domain.category.CategoryName;
import jakarta.annotation.Nullable;

import java.time.Instant;
import java.util.List;

public record CategoryEntity(
  Long id,
  String name,
  @Nullable Instant deletedAt,
  Long version
) {
  public static CategoryEntity from(Category category) {
    return new CategoryEntity(
      category.id.value(),
      category.name().value(),
      category.deletedAt(),
      category.version().value() - 1
    );
  }

  static List<Category> from(List<CategoryEntity> entities) {
    return CollectionsUtils.streamOf(entities)
      .map(CategoryEntity::to)
      .toList();
  }

  Category to() {
    return new Category(
      new CategoryId(id),
      Version.from(version),
      new CategoryName(name),
      deletedAt
    );
  }
}
