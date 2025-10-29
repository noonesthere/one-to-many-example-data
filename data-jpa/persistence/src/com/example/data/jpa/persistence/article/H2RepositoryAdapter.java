package com.example.data.jpa.persistence.article;


import com.example.common.types.DomainEvent;
import com.example.domain.article.Article;
import com.example.domain.article.ArticleId;
import com.example.scenarios.dto.article.ArticleReadModel;
import com.example.scenarios.outbound.article.ArticleExtractorOutPort;
import com.example.scenarios.outbound.article.ArticlePersisterOutPort;
import com.example.scenarios.outbound.article.ArticlesExtractorOutPort;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class H2RepositoryAdapter implements ArticlePersisterOutPort, ArticlesExtractorOutPort, ArticleExtractorOutPort {

  private final ArticleRepository articleRepository;
  private final ApplicationEventPublisher eventPublisher;

  H2RepositoryAdapter(
    ArticleRepository articleRepository,
    ApplicationEventPublisher eventPublisher
  ) {
    this.articleRepository = articleRepository;
    this.eventPublisher = eventPublisher;
  }

  @Transactional
  @Override
  public void persist(Article article) {
    final List<DomainEvent> events = article.popEvents();

    if (events.isEmpty()) {
      throw new IllegalStateException("Stub when persist");
    }

    final var entity = ArticleEntity.from(article);
    articleRepository.save(entity);
    events.forEach(eventPublisher::publishEvent);
  }

  @Override
  public List<ArticleReadModel> findAll() {
    return List.of();
  }

  @Override
  public Article get(ArticleId id) {
    return articleRepository
      .findById(id.value())
      .map(ArticleEntity::to)
      .orElseThrow();
  }
}
