package com.example.data.jdbc.persistence.article;

import com.example.domain.article.Article;
import com.example.scenarios.outbound.article.ArticleCategoryChanger;
import com.example.scenarios.outbound.article.ArticleParagraphEditor;
import com.example.scenarios.outbound.article.ArticleParagraphRemover;
import com.example.scenarios.outbound.article.ArticleRateChanger;
import org.springframework.stereotype.Component;

@Component
class H2ArticleRepositoryUpdater implements
  ArticleParagraphEditor,
  ArticleParagraphRemover,
  ArticleCategoryChanger,
  ArticleRateChanger {

  private final PartialUpdater<Article> partialUpdater;

  H2ArticleRepositoryUpdater(
    ArticlePartialUpdater partialUpdater
  ) {
    this.partialUpdater = partialUpdater;
  }

  @Override
  public void changeParagraph(Article article) {
    update(article);
  }

  @Override
  public void remove(Article article) {
    update(article);
  }

  @Override
  public void changeCategory(Article article) {
    update(article);
  }

  @Override
  public void changeRate(Article article) {
    update(article);
  }

  private void update(Article article) {
    article.popEvents().forEach(e -> {
        final int result = partialUpdater.update(e, article);
        System.out.println(result);
      }
    );
  }
}
