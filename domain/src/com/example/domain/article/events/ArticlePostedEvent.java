package com.example.domain.article.events;

import java.time.Instant;
import java.util.UUID;

public record ArticlePostedEvent(
  UUID id,
  Instant createdAt,
  Long articleId,
  Instant publishedAt,
  Long categoryId
) implements ArticleEvent {

  public static ArticlePostedEvent create(Long articleId, Instant publishedAt, Long categoryId) {
    return new ArticlePostedEvent(
      UUID.randomUUID(),
      Instant.now(),
      articleId,
      publishedAt,
      categoryId
    );
  }

  @Override
  public Long domainId() {
    return articleId;
  }
}
