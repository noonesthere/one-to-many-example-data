package com.example.data.jdbc.log.article;

import com.example.data.jdbc.log.persistence.article.H2ArticleEventRepositoryAdapter;
import com.example.domain.article.events.ArticleEvent;
import com.example.domain.article.events.ArticlePostedEvent;
import com.example.domain.article.events.ArticleRateChangedEvent;
import com.example.domain.article.events.ArticleTitleRenamedEvent;
import com.example.domain.article.events.ParagraphAddedEvent;
import com.example.domain.article.events.ParagraphEditedEvent;
import com.example.domain.article.events.ParagraphRemovedEvent;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;


@Service
class ArticleEventListener {

  private final H2ArticleEventRepositoryAdapter repositoryAdapter;

  ArticleEventListener(H2ArticleEventRepositoryAdapter repositoryAdapter) {
    this.repositoryAdapter = repositoryAdapter;
  }

  @ApplicationModuleListener
  public void on(ArticlePostedEvent event) {
    handle(event);
  }

  @ApplicationModuleListener
  public void on(ParagraphAddedEvent event) {
    handle(event);
  }

  @ApplicationModuleListener
  public void on(ArticleRateChangedEvent event) {
    handle(event);
  }

  @ApplicationModuleListener
  public void on(ArticleTitleRenamedEvent event) {
    handle(event);
  }

  @ApplicationModuleListener
  public void on(ParagraphEditedEvent event) {
    handle(event);
  }

  @ApplicationModuleListener
  public void on(ParagraphRemovedEvent event) {
    handle(event);
  }

  private void handle(ArticleEvent event) {
    repositoryAdapter.persist(event);
  }
}
