package com.example.data.jpa.persistence.category;

import com.example.common.types.Version;
import com.example.domain.category.Category;
import com.example.domain.category.CategoryId;
import com.example.domain.category.CategoryName;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "CATEGORY")
public class CategoryEntity {

  @Id
  @Column(name = "ID", nullable = false, updatable = false)
  private Long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Nullable
  @Column(name = "DELETED_AT")
  private Instant deletedAt;

  @Column(name = "VERSION")
  private Long version;

  // --- Constructors ---
  protected CategoryEntity() {
    // required by JPA
  }

  public CategoryEntity(Long id, String name, @Nullable Instant deletedAt, Long version) {
    this.id = id;
    this.name = name;
    this.deletedAt = deletedAt;
    this.version = version;
  }

  // --- Domain Conversion ---
  public static CategoryEntity from(Category category) {
    return new CategoryEntity(
      category.id.value(),
      category.name().value(),
      category.deletedAt(),
      category.version().value() // JPA will increment on update
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

  // --- Getters (JPA requires them or field access) ---
  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Instant getDeletedAt() {
    return deletedAt;
  }

  public Long getVersion() {
    return version;
  }
}

