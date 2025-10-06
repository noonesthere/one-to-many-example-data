package com.example.scenarios.inbound.category;

import com.example.domain.category.Category;

public interface CreateCategory {
  Category execute(String name);
}
