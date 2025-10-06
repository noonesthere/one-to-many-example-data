package com.example.plain.jdbc.persistence;

import com.example.domain.category.Category;
import com.example.scenarios.outbound.category.CategoryExtractor;
import com.example.scenarios.outbound.category.CategoryPersister;
import jakarta.inject.Named;

import java.util.List;

@Named
class H2CategoryRepositoryAdapter implements CategoryPersister, CategoryExtractor {

  private final CategoryRepository repository;

  H2CategoryRepositoryAdapter(CategoryRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Category> getAll() {
    return repository.selectAll();
  }

  @Override
  public Category persist(Category category) {
    return repository.insert(category);
  }

}
