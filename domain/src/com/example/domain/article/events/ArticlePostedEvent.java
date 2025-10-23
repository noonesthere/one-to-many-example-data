package com.example.domain.article.events;

import com.example.domain.article.ArticleId;
import com.example.domain.category.CategoryId;

import java.time.Instant;
import java.util.UUID;

public record ArticlePostedEvent(
  UUID id,
  Instant createdAt,
  Long articleId,
  Instant publishedAt,
  Long categoryId
) implements ArticleEvent {

  public static ArticlePostedEvent create(ArticleId articleId, Instant publishedAt, CategoryId categoryId) {
    return new ArticlePostedEvent(
      UUID.randomUUID(),
      Instant.now(),
      articleId.value(),
      publishedAt,
      categoryId.value()
    );
  }

  @Override
  public Long domainId() {
    return articleId;
  }
}
