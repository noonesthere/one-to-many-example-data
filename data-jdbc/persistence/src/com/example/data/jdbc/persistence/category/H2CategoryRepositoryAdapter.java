package com.example.data.jdbc.persistence.category;

import com.example.common.types.DomainEvent;
import com.example.domain.category.Category;
import com.example.domain.category.CategoryId;
import com.example.scenarios.outbound.category.CategoriesExtractor;
import com.example.scenarios.outbound.category.CategoryExtractor;
import com.example.scenarios.outbound.category.CategoryPersister;
import com.example.scenarios.outbound.category.CategoryUpdater;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Component
class H2CategoryRepositoryAdapter implements
  CategoryPersister,
  CategoriesExtractor,
  CategoryUpdater,
  CategoryExtractor {

  private final CategoryRepository categoryRepository;
  private final ApplicationEventPublisher eventPublisher;

  H2CategoryRepositoryAdapter(CategoryRepository categoryRepository, ApplicationEventPublisher eventPublisher) {
    this.categoryRepository = categoryRepository;
    this.eventPublisher = eventPublisher;
  }

  @Override
  public Category get(CategoryId id) {
    return categoryRepository.findById(id.value())
      .map(CategoryEntity::to)
      .orElseThrow();
  }

  @Override
  public List<Category> getAll() {
    final List<CategoryEntity> all = categoryRepository.findAll();
    return all.stream().map(CategoryEntity::to).toList();
  }

  @Transactional
  @Override
  public Category persist(Category category) {
    final List<DomainEvent> domainEvents = category.popEvents();

    if (Objects.nonNull(domainEvents)) {
      final CategoryEntity entity = CategoryEntity.from(category);
      final var saved = categoryRepository.save(entity);
      domainEvents.forEach(eventPublisher::publishEvent);
      return saved.to();
    } else {
      throw new IllegalStateException("Stub "); // TODO: fix me
    }
  }

  @Transactional
  @Override
  public Category update(Category category) {
    category.popEvents().forEach(eventPublisher::publishEvent);
    return category;
  }
}
