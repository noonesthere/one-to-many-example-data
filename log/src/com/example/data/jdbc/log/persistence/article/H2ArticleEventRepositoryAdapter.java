package com.example.data.jdbc.log.persistence.article;

import com.example.domain.article.events.ArticleEvent;
import org.springframework.stereotype.Repository;

@Repository
public class H2ArticleEventRepositoryAdapter {

  private final ArticleEventPublicationRepository repository;
  private final ArticleEventMapper mapper;

  H2ArticleEventRepositoryAdapter(ArticleEventPublicationRepository repository, ArticleEventMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public void persist(ArticleEvent event) {
    repository.save(mapper.toEntity(event));
  }
}
