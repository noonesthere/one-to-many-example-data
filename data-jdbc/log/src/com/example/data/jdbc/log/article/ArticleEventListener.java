package com.example.data.jdbc.log.article;

import com.example.domain.article.events.ArticleEvent;
import com.example.domain.article.events.ArticlePostedEvent;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;


@Service
class ArticleEventListener {
  @ApplicationModuleListener
  public void on(ArticlePostedEvent event) {
    handle(event);
  }

  private void handle(ArticleEvent event) {
    System.out.println("Received event in right handler: " + event.getClass());
  }
}
