package com.example.data.jdbc.persistence.article;

import com.example.domain.article.Article;
import com.example.scenarios.outbound.article.ArticleUpdater;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
class H2ArticleRepositoryUpdater implements ArticleUpdater {
  private final ApplicationEventPublisher eventPublisher;

  H2ArticleRepositoryUpdater(ApplicationEventPublisher eventPublisher
  ) {
    this.eventPublisher = eventPublisher;
  }

  @Transactional
  @Override
  public void update(Article article) {
    article.popEvents().forEach(eventPublisher::publishEvent);
  }
}
