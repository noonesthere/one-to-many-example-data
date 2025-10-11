package com.example.data.jdbc.persistence.article;

import com.example.common.types.DomainEvent;
import com.example.common.utilities.CollectionsUtils;
import com.example.domain.article.Article;
import com.example.domain.article.ArticleId;
import com.example.scenarios.outbound.article.ArticleExtractor;
import com.example.scenarios.outbound.article.ArticlePersister;
import com.example.scenarios.outbound.article.ArticlesExtractor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
class H2RepositoryAdapter implements ArticlePersister, ArticlesExtractor, ArticleExtractor {

  private final ArticlePartialUpdater articlePartialUpdater;
  private final ArticleRepository articleRepository;
  private final ApplicationEventPublisher eventPublisher;

  H2RepositoryAdapter(
    ArticlePartialUpdater articlePartialUpdater,
    ArticleRepository articleRepository,
    ApplicationEventPublisher eventPublisher
  ) {
    this.articlePartialUpdater = articlePartialUpdater;
    this.articleRepository = articleRepository;
    this.eventPublisher = eventPublisher;
  }

  @Transactional
  @Override
  public void persist(Article article) {
    final List<DomainEvent> events = article.popEvents();

    if (!events.isEmpty()) {
      final ArticleEntity entity = ArticleEntity.from(article);
//      articleRepository.save(entity);
      articlePartialUpdater.update(events, entity);
      events.forEach(eventPublisher::publishEvent);
    } else {
      throw new IllegalStateException("Stub when persist");
    }
  }

  @Override
  public List<Article> findAll() {
    return CollectionsUtils
      .streamOf(articleRepository.findAll())
      .map(ArticleEntity::to)
      .toList();
  }

  @Override
  public Article get(ArticleId id) {
    return articleRepository
      .findById(id.value())
      .map(ArticleEntity::to)
      .orElseThrow();
  }
}
