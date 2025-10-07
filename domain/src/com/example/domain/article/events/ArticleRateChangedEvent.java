package com.example.domain.article.events;

import com.example.common.types.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record ArticleRateChangedEvent(
  UUID id,
  Instant createdAt,
  Long articleId,
  Double rating,
  Integer count
) implements ArticleEvent {

  public static ArticleRateChangedEvent create(
    Long articleId,
    Double value,
    Integer count
  ) {
    return new ArticleRateChangedEvent(UUID.randomUUID(), Instant.now(), articleId, value, count);
  }

  @Override
  public Long domainId() {
    return articleId;
  }
}
