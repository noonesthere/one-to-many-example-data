package com.example.scenarios.category;

import com.example.domain.category.Category;
import com.example.scenarios.inbound.category.GetCategories;
import com.example.scenarios.outbound.category.CategoriesExtractor;
import jakarta.inject.Named;

import java.util.List;

@Named
class GetCategoriesUseCase implements GetCategories {

  private final CategoriesExtractor extractor;

  GetCategoriesUseCase(CategoriesExtractor extractor) {
    this.extractor = extractor;
  }

  @Override
  public List<Category> execute() {
    return extractor.getAll();
  }
}
