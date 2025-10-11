package com.example.data.jdbc.persistence.category;

import com.example.common.utilities.CollectionsUtils;
import com.example.domain.article.events.ArticlePostedEvent;
import com.example.domain.category.CategoryCounter;
import com.example.scenarios.outbound.category.CategoriesCounterExtractor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
  public void onPostArticle(ArticlePostedEvent event) {
    Optional<CategoryCounterEntity> entity = repository.findById(event.categoryId());
    final var categoryCounter = entity.map(CategoryCounterEntity::to).orElseThrow();

    repository.save(CategoryCounterEntity.from(categoryCounter.increase())); // do not use events for such changes
  }
}
