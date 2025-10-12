package com.example.data.jdbc.persistence.article;

import com.example.common.types.DomainEvent;
import com.example.domain.article.Article;
import com.example.scenarios.outbound.article.ArticleUpdater;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
class H2ArticleRepositoryUpdater implements ArticleUpdater {

  private final ArticlePartialUpdater partialUpdater;
  private final ApplicationEventPublisher eventPublisher;

  H2ArticleRepositoryUpdater(
    ArticlePartialUpdater partialUpdater,
    ApplicationEventPublisher eventPublisher
  ) {
    this.partialUpdater = partialUpdater;
    this.eventPublisher = eventPublisher;
  }

  @Transactional
  @Override
  public void update(Article article) {
    final List<DomainEvent> domainEvents = article.popEvents();
    if (!domainEvents.isEmpty()) {
      final var entity = ArticleEntity.from(article);
      // TODO: can be re-write by each interface
      partialUpdater.update(domainEvents, entity);
      domainEvents.forEach(eventPublisher::publishEvent);
    }
  }
}
