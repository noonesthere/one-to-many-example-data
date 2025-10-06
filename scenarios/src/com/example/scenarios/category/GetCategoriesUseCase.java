package com.example.scenarios.category;

import com.example.domain.category.Category;
import com.example.scenarios.inbound.category.GetCategories;
import com.example.scenarios.outbound.category.CategoryExtractor;
import jakarta.inject.Named;

import java.util.List;

@Named
class GetCategoriesUseCase implements GetCategories {

  private final CategoryExtractor extractor;

  GetCategoriesUseCase(CategoryExtractor extractor) {
    this.extractor = extractor;
  }

  @Override
  public List<Category> execute() {
    return extractor.getAll();
  }
}
