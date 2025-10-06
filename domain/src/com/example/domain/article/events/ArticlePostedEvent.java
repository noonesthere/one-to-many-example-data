package com.example.domain.article.events;

import java.time.Instant;
import java.util.List;

public record ArticlePostedEvent(
  Long articleId,
  List<Long> pids,
  Instant publishedAt
) implements ArticleEvent {
}
