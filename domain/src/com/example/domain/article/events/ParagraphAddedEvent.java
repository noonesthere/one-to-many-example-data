package com.example.domain.article.events;

import com.example.common.types.Version;

import java.time.Instant;
import java.util.UUID;

public record ParagraphAddedEvent(
  UUID id,
  Instant createdAt,
  Long articleId,
  Long paragraphId,
  String text,
  Long version
) implements ArticleEvent {

  public static ParagraphAddedEvent create(Long articleId, Long paragraphId, String text, Version version) {
    return new ParagraphAddedEvent(UUID.randomUUID(), Instant.now(), articleId, paragraphId, text, version.value());
  }
}
