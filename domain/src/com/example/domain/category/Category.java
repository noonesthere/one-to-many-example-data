package com.example.domain.category;

import com.example.common.types.DomainEntity;
import com.example.common.types.Version;

public class Category extends DomainEntity<CategoryId> {

  private final CategoryName name;
  // other fields but simplified for demonstration purpose

  public Category(CategoryId categoryId, Version version, CategoryName name) {
    super(categoryId, version);
    this.name = name;
  }
}
