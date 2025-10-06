package com.example.data.jdbc.persistence;

import com.example.domain.category.Category;
import com.example.scenarios.outbound.category.CategoryExtractor;
import com.example.scenarios.outbound.category.CategoryPersister;
import jakarta.inject.Named;

import java.util.List;

@Named
class H2CategoryRepositoryAdapter implements CategoryPersister, CategoryExtractor {

  private final CategoryRepository categoryRepository;

  H2CategoryRepositoryAdapter(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public Category invoke(Category category) {
    final CategoryEntity entity = CategoryEntity.from(category);

    final var saved = categoryRepository.save(entity);
    return saved.to();
  }

  @Override
  public List<Category> invoke() {
    final List<CategoryEntity> all = categoryRepository.findAll();
    return all.stream().map(CategoryEntity::to).toList();
  }
}
