package com.example.domain.article.events;

import com.example.common.types.Version;
import com.example.domain.article.ArticleId;
import com.example.domain.article.ParagraphId;

import java.time.Instant;
import java.util.UUID;

public record ParagraphEditedEvent(
  UUID id,
  Instant createdAt,
  Long articleId,
  Long paragraphId,
  String text,
  Long version
) implements ArticleEvent {

  public static ParagraphEditedEvent create(ArticleId articleId, ParagraphId paragraphId, String text, Version version) {
    return new ParagraphEditedEvent(
      UUID.randomUUID(),
      Instant.now(),
      articleId.value(),
      paragraphId.value(),
      text,
      version.value()
    );
  }
}
