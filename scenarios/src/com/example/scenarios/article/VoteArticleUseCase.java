package com.example.scenarios.article;

import com.example.domain.article.ArticleId;
import com.example.domain.article.commands.VoteCommand;
import com.example.scenarios.dto.article.VoteArticleInput;
import com.example.scenarios.inbound.article.VoteArticleInPort;
import com.example.scenarios.outbound.article.ArticleExtractorOutPort;
import com.example.scenarios.outbound.article.ArticleUpdaterOutPort;
import jakarta.inject.Named;

@Named
class VoteArticleUseCase implements VoteArticleInPort {

  private final ArticleExtractorOutPort articleExtractorOutPort;
  private final ArticleUpdaterOutPort articleUpdaterOutPort;

  VoteArticleUseCase(ArticleExtractorOutPort articleExtractorOutPort, ArticleUpdaterOutPort articleUpdaterOutPort) {
    this.articleExtractorOutPort = articleExtractorOutPort;
    this.articleUpdaterOutPort = articleUpdaterOutPort;
  }


  @Override
  public void execute(VoteArticleInput input) {
    final var articleId = new ArticleId(input.articleId());
    final var article = articleExtractorOutPort.get(articleId);
    final var command = new VoteCommand(input.grade());
    article.vote(command);
    articleUpdaterOutPort.update(article);
  }
}
