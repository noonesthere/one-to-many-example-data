package com.example.scenarios.outbound.category;

import com.example.domain.category.Category;

public interface CategoryPersisterOutPort {
  Category persist(Category category);
}
