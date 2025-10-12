package com.example.domain.article.events;

import com.example.common.types.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record ParagraphRemovedEvent(
  UUID id,
  Instant createdAt,
  Long articleId,
  Long paragraphId
) implements ArticleEvent {

  public static DomainEvent create(Long articleId, Long paragraphId) {
    return new ParagraphRemovedEvent(UUID.randomUUID(), Instant.now(), articleId, paragraphId);
  }

  @Override
  public Long domainId() {
    return articleId;
  }
}
