package com.example.data.jpa.persistence.category;

import com.example.domain.category.Category;
import com.example.scenarios.outbound.category.CategoriesExtractorOutPort;
import com.example.scenarios.outbound.category.CategoryPersisterOutPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class H2CategoryRepositoryAdapter implements CategoryPersisterOutPort, CategoriesExtractorOutPort {

  private final CategoryRepository repository;

  H2CategoryRepositoryAdapter(CategoryRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Category> getAll() {
    return repository.findAll().stream().map(CategoryEntity::to).toList();
  }

  @Override
  public Category persist(Category category) {
    final var entity = CategoryEntity.from(category);
    return repository.save(entity).to();
  }
}
