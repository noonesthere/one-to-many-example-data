package com.example.scenarios.inbound.category;

import com.example.domain.category.CategoryId;

public interface CreateCategory {
  CategoryId execute(String name);
}
