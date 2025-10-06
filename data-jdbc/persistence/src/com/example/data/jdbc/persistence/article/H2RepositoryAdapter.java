package com.example.data.jdbc.persistence.article;

import com.example.common.types.DomainEvent;
import com.example.domain.article.Article;
import com.example.scenarios.outbound.ArticlePersister;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class H2RepositoryAdapter implements ArticlePersister {

  private final ArticleRepository articleRepository;

  H2RepositoryAdapter(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  @Override
  public void persist(Article article) {
    final List<DomainEvent> domainEvents = article.popEvents();

    if (!domainEvents.isEmpty()) {
      final  ArticleEntity entity = ArticleEntity.from(article);
    }
  }
}
