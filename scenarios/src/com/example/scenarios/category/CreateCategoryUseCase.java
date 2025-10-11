package com.example.scenarios.category;

import com.example.domain.category.Category;
import com.example.domain.category.CategoryIdProvider;
import com.example.domain.category.CategoryName;
import com.example.domain.category.commands.CreateCategoryCommand;
import com.example.scenarios.inbound.category.CreateCategory;
import com.example.scenarios.outbound.category.CategoryPersister;
import jakarta.inject.Named;

@Named
class CreateCategoryUseCase implements CreateCategory {

  private final CategoryPersister persister;
  private final CategoryIdProvider categoryIdGenerator;

  public CreateCategoryUseCase(
    CategoryPersister persister,
    CategoryIdProvider categoryIdGenerator
  ) {
    this.persister = persister;
    this.categoryIdGenerator = categoryIdGenerator;
  }

  @Override
  public Category execute(String name) {
    final var categoryId = categoryIdGenerator.provide();
    final var categoryName = new CategoryName(name);
    final var createCategoryCommand = new CreateCategoryCommand(categoryId, categoryName);
    final var category = Category.create(createCategoryCommand);

    return persister.persist(category);
  }
}
