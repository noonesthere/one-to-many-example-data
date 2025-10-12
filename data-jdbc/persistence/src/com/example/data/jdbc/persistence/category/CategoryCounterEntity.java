package com.example.data.jdbc.persistence.category;

import com.example.domain.category.CategoryCounter;
import com.example.domain.category.CategoryId;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("CATEGORY_COUNTER")
public record CategoryCounterEntity(
  @Id @Column("CATEGORY_ID") Long categoryId,
  @Column("COUNTER") Integer value
) implements Persistable<Long> {

  CategoryCounter to() {
    return new CategoryCounter(new CategoryId(categoryId), value);
  }

  static CategoryCounterEntity from(CategoryCounter categoryCounter) {
    return new CategoryCounterEntity(categoryCounter.categoryId().value(), categoryCounter.value());
  }

  @Override
  public Long getId() {
    return categoryId;
  }

  @Override
  public boolean isNew() {
    return value == 0;
  }
}
