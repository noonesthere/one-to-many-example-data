package com.example.data.jdbc.persistence.category;

import com.example.common.types.Version;
import com.example.domain.category.Category;
import com.example.domain.category.CategoryId;
import com.example.domain.category.CategoryName;
import jakarta.annotation.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("CATEGORY")
public record CategoryEntity(
  @Id @Column("ID") Long id,
  @Column("NAME") String name,
  @Column("DELETED_AT") @Nullable Instant deletedAt,
  @Column("VERSION") Long version
) implements Persistable<Long> {

  static CategoryEntity from(Category category) {
    return new CategoryEntity(
      category.id.value(),
      category.name().value(),
      category.deletedAt(),
      category.version().value() - 1
    );
  }

  public Category to() {
    return new Category(
      new CategoryId(id),
      Version.from(version),
      new CategoryName(name),
      deletedAt
    );
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public boolean isNew() {
    return version == 1;
  }
}
