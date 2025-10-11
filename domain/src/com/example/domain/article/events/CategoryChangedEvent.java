package com.example.domain.article.events;

import java.time.Instant;
import java.util.UUID;

public record CategoryChangedEvent(
  UUID id,
  Instant createdAt,
  Long articleId,
  Long categoryId
) implements ArticleEvent {

  public static CategoryChangedEvent create(Long articleId, Long categoryId) {
    return new CategoryChangedEvent(UUID.randomUUID(), Instant.now(), articleId, categoryId);
  }

  @Override
  public Long domainId() {
    return articleId;
  }
}
