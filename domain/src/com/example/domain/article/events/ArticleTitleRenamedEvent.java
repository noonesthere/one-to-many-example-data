package com.example.domain.article.events;

import com.example.common.types.DomainEvent;
import com.example.common.types.Version;
import com.example.domain.article.ArticleId;
import com.example.domain.article.Title;

import java.time.Instant;
import java.util.UUID;

public record ArticleTitleRenamedEvent(
  UUID id,
  Instant createdAt,
  Long articleId,
  String title,
  Long previousVersion
) implements ArticleEvent {

  public static DomainEvent create(ArticleId articleId, Title title, Version version) {
    return new ArticleTitleRenamedEvent(
      UUID.randomUUID(),
      Instant.now(),
      articleId.value(),
      title.value(),
      version.value()
    );
  }

  @Override
  public Long domainId() {
    return articleId;
  }

}
