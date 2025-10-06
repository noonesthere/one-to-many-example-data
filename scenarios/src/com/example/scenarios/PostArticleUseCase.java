package com.example.scenarios;

import com.example.domain.article.Article;
import com.example.domain.article.commands.PostArticleCommand;
import com.example.scenarios.dto.ArticleDto;
import com.example.scenarios.inbound.article.PostArticle;
import com.example.scenarios.outbound.ArticlePersister;
import jakarta.inject.Named;

@Named
class PostArticleUseCase implements PostArticle {

//  private final ArticlePersister articlePersister;
//
//  public PostArticleUseCase(ArticlePersister articlePersister) {
//    this.articlePersister = articlePersister;
//  }

  @Override
  public void post(ArticleDto articleDto) {
//    final var postArticleCommand = new PostArticleCommand();
//    final var article = Article.post(postArticleCommand);
//    articlePersister.persist(article);
  }
}
