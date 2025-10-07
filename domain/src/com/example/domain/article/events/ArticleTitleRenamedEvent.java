package com.example.domain.article.events;

import com.example.common.types.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record ArticleTitleRenamedEvent(
  UUID id,
  Instant createdAt,
  Long articleId,
  String title
) implements ArticleEvent {

  public static DomainEvent create(Long articleId, String title) {
    return new ArticleTitleRenamedEvent(UUID.randomUUID(), Instant.now(), articleId, title
    );
  }

  @Override
  public Long domainId() {
    return articleId;
  }

}
