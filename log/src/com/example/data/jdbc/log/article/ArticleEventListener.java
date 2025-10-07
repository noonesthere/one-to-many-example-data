package com.example.data.jdbc.log.article;

import com.example.data.jdbc.log.persistence.article.H2ArticleEventRepositoryAdapter;
import com.example.domain.article.events.ArticleEvent;
import com.example.domain.article.events.ArticlePostedEvent;
import com.example.domain.article.events.ArticleRateChangedEvent;
import com.example.domain.article.events.ParagraphAddedEvent;
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

  private void handle(ArticleEvent event) {
    System.out.println(" =================        Received event in right handler: " + event.getClass() + "=========================================");
    repositoryAdapter.persist(event);
  }
}
