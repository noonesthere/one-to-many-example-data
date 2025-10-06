package com.example.scenarios.outbound.category;

import com.example.domain.category.Category;

public interface CategoryPersister {
  Category persist(Category category);
}
