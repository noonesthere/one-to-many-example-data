package com.example.domain.article.events;

import com.example.common.types.DomainEvent;
import com.example.common.types.Version;
import com.example.domain.article.ArticleId;
import com.example.domain.article.ParagraphId;

import java.time.Instant;
import java.util.UUID;

public record ParagraphRemovedEvent(
  UUID id,
  Instant createdAt,
  Long articleId,
  Long paragraphId,
  Long version
) implements ArticleEvent {

  public static DomainEvent create(ArticleId articleId, ParagraphId paragraphId, Version version) {
    return new ParagraphRemovedEvent(
      UUID.randomUUID(),
      Instant.now(),
      articleId.value(),
      paragraphId.value(),
      version.value()
    );
  }
}
