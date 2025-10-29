package com.example.scenarios.outbound.category;

import com.example.domain.category.CategoryCounter;

import java.util.List;

public interface CategoriesCounterExtractorOutPort {
  List<CategoryCounter> getAll();

}
