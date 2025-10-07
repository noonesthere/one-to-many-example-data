package com.example.domain.article.events;

import java.time.Instant;
import java.util.UUID;

public record ParagraphAddedEvent(
  UUID id,
  Instant createdAt,
  Long articleId,
  Long paragraphId,
  String text
) implements ArticleEvent {

  public static ParagraphAddedEvent create(Long articleId, Long paragraphId, String text) {
    return new ParagraphAddedEvent(UUID.randomUUID(), Instant.now(), articleId, paragraphId, text);
  }

  @Override
  public Long domainId() {
    return articleId;
  }

}
