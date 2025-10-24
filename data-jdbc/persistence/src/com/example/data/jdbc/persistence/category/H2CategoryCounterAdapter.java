package com.example.data.jdbc.persistence.category;

import com.example.common.utilities.CollectionsUtils;
import com.example.domain.article.events.ArticlePostedEvent;
import com.example.domain.category.CategoryCounter;
import com.example.domain.category.CategoryId;
import com.example.domain.category.events.CategoryCreatedEvent;
import com.example.scenarios.outbound.category.CategoriesCounterExtractor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class H2CategoryCounterAdapter implements CategoriesCounterExtractor {

  private final CategoryCounterRepository repository;

  H2CategoryCounterAdapter(CategoryCounterRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<CategoryCounter> getAll() {
    return CollectionsUtils.streamOf(repository.findAll())
      .map(CategoryCounterEntity::to)
      .toList();
  }

  @EventListener
  void onPostArticle(ArticlePostedEvent event) {
    final CategoryCounter categoryCounter = repository.findById(event.categoryId())
      .map(CategoryCounterEntity::to)
      .orElseThrow();

    repository.save(CategoryCounterEntity.from(categoryCounter.increase()));
  }

  @EventListener
  void onCreateCategory(CategoryCreatedEvent event) {
    Long categoryIdValue = event.categoryId();
    final var categoryCounter = CategoryCounter.newCounter(new CategoryId(categoryIdValue));
    repository.save(CategoryCounterEntity.from(categoryCounter));
  }
}
