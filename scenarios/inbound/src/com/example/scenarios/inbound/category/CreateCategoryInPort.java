package com.example.scenarios.inbound.category;

import com.example.domain.category.CategoryId;

public interface CreateCategoryInPort {
  CategoryId execute(String name);
}
