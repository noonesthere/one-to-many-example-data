package com.example.scenarios.article;

import com.example.domain.article.ArticleId;
import com.example.domain.article.commands.VoteCommand;
import com.example.scenarios.dto.article.VoteArticleInput;
import com.example.scenarios.inbound.article.VoteArticle;
import com.example.scenarios.outbound.article.ArticleExtractor;
import com.example.scenarios.outbound.article.ArticleUpdater;
import jakarta.inject.Named;

@Named
class VoteArticleUseCase implements VoteArticle {

  private final ArticleExtractor articleExtractor;
  private final ArticleUpdater articleUpdater;

  VoteArticleUseCase(ArticleExtractor articleExtractor, ArticleUpdater articleUpdater) {
    this.articleExtractor = articleExtractor;
    this.articleUpdater = articleUpdater;
  }


  @Override
  public void execute(VoteArticleInput input) {
    final var articleId = new ArticleId(input.articleId());
    final var article = articleExtractor.get(articleId);
    final var command = new VoteCommand(input.grade());
    article.vote(command);
    articleUpdater.update(article);
  }
}
