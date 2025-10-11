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
import java.util.Objects;

@Table("CATEGORY")
public record CategoryEntity(
  @Id @Column("ID") Long id,
  @Column("NAME") String name,
  @Column("UPDATED_AT") Instant updatedAt,
  @Column("DELETED_AT") @Nullable Instant deletedAt,
  @Column("VERSION") @org.springframework.data.annotation.Version Long version
) implements Persistable<Long> {

  static CategoryEntity from(Category category) {
    return new CategoryEntity(
      category.id.value(),
      category.name().asStringValue(),
      category.updatedAt(),
      category.deletedAt(),
      category.version().value() - 1
    );
  }

  public Category to() {
    return new Category(
      new CategoryId(id),
      Version.from(version),
      CategoryName.from(name),
      updatedAt,
      deletedAt
    );
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public boolean isNew() {
    return Objects.isNull(updatedAt);
  }
}
