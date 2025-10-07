package com.example.domain.article.events;

import java.time.Instant;
import java.util.UUID;

public record ParagraphEditedEvent(
  UUID id,
  Instant createdAt,
  Long articleId,
  Long paragraphId,
  String text
) implements ArticleEvent {

  public static ParagraphEditedEvent create(Long articleId, Long paragraphId, String text) {
    return new ParagraphEditedEvent(UUID.randomUUID(), Instant.now(), articleId, paragraphId, text);
  }

  @Override
  public Long domainId() {
    return articleId;
  }
}
