package com.example.domain.article.events;

import com.example.common.types.Version;
import com.example.domain.article.ArticleId;
import com.example.domain.article.Rating;

import java.time.Instant;
import java.util.UUID;

public record ArticleRateChangedEvent(
  UUID id,
  Instant createdAt,
  Long articleId,
  Double rating,
  Integer count,
  Long previousVersion
) implements ArticleEvent {

  public static ArticleRateChangedEvent create(
    ArticleId articleId,
    Rating rating,
    Version version
  ) {
    return new ArticleRateChangedEvent(
      UUID.randomUUID(),
      Instant.now(),
      articleId.value(),
      rating.value(),
      rating.count(),
      version.value()
    );
  }

  @Override
  public Long domainId() {
    return articleId;
  }
}
