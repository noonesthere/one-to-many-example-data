package com.example.data.jdbc.persistence.article;

import com.example.common.types.DomainEvent;
import com.example.domain.article.Article;
import com.example.scenarios.outbound.article.ArticleUpdater;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class H2ArticleRepositoryUpdater implements ArticleUpdater {

  private final ArticlePartialUpdater updater;
  private final ApplicationEventPublisher eventPublisher;

  H2ArticleRepositoryUpdater(ArticlePartialUpdater updater, ApplicationEventPublisher eventPublisher) {
    this.updater = updater;
    this.eventPublisher = eventPublisher;
  }

  @Override
  public void update(Article article) {
    List<DomainEvent> domainEvents = article.popEvents();
    if (!domainEvents.isEmpty()) {
      ArticleEntity entity = ArticleEntity.from(article);
      updater.update(domainEvents, entity);
      domainEvents.forEach(eventPublisher::publishEvent);
    } else {
      throw new IllegalStateException("invalid state");
    }
  }
}
