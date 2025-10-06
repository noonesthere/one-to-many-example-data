package com.example.data.jpa.persistence.category;

import com.example.domain.category.Category;
import com.example.scenarios.outbound.category.CategoryExtractor;
import com.example.scenarios.outbound.category.CategoryPersister;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class H2CategoryRepositoryAdapter implements CategoryPersister, CategoryExtractor {

  private final CategoryRepository repository;

  H2CategoryRepositoryAdapter(CategoryRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Category> invoke() {
    return repository.findAll().stream().map(CategoryEntity::to).toList();
  }

  @Override
  public Category invoke(Category category) {
    final var entity = CategoryEntity.from(category);
    return repository.save(entity).to();
  }
}
