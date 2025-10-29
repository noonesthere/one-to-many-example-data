package com.example.domain.article.events;

import com.example.common.types.Version;
import com.example.domain.article.ArticleId;
import com.example.domain.category.CategoryId;

import java.time.Instant;
import java.util.UUID;

public record CategoryChangedEvent(
  UUID id,
  Instant createdAt,
  Long articleId,
  Long categoryId,
  Long version
) implements ArticleEvent {

  public static CategoryChangedEvent create(ArticleId articleId, CategoryId categoryId, Version previousVersion) {
    return new CategoryChangedEvent(
      UUID.randomUUID(),
      Instant.now(),
      articleId.value(),
      categoryId.value(),
      previousVersion.value()
    );
  }
}
