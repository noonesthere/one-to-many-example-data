package com.example.scenarios.article;

import com.example.domain.article.ArticleId;
import com.example.domain.article.commands.VoteCommand;
import com.example.scenarios.dto.article.VoteArticleInput;
import com.example.scenarios.inbound.article.VoteArticle;
import com.example.scenarios.outbound.ArticlePersister;
import com.example.scenarios.outbound.article.ArticleExtractor;
import jakarta.inject.Named;

@Named
class VoteArticleUseCase implements VoteArticle {

  private final ArticleExtractor articleExtractor;
  private final ArticlePersister persister;

  VoteArticleUseCase(ArticleExtractor articleExtractor, ArticlePersister persister) {
    this.articleExtractor = articleExtractor;
    this.persister = persister;
  }

  @Override
  public void exucute(VoteArticleInput input) {
    final var articleId = ArticleId.from(input.articleId());
    final var article = articleExtractor.get(articleId);
    final var command = new VoteCommand(input.grade());
    article.vote(command);
    persister.persist(article);
  }
}
