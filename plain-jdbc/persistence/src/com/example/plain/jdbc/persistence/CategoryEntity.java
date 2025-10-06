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
  Instant updatedAt,
  @Nullable Instant deletedAt,
  Long version
) {
  public static CategoryEntity from(Category category) {
    return new CategoryEntity(
      category.id.asLongValue(),
      category.name.asStringValue(),
      category.updatedAt(),
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
      CategoryId.from(id),
      Version.from(version),
      CategoryName.from(name),
      updatedAt,
      deletedAt
    );
  }
}
