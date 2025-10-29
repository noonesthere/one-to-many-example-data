package com.example.scenarios.outbound.category;

import com.example.domain.category.Category;
import com.example.domain.category.CategoryId;

public interface CategoryExtractorOutPort {
  Category get(CategoryId id);
}
