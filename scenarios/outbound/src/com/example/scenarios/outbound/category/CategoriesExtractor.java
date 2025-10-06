package com.example.scenarios.outbound.category;

import com.example.domain.category.Category;

import java.util.List;

public interface CategoriesExtractor {
  List<Category> getAll();
}
