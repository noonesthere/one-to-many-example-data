package com.example.scenarios.article;

import com.example.domain.article.ArticleId;
import com.example.domain.article.Title;
import com.example.domain.article.commands.RenameTitleCommand;
import com.example.scenarios.dto.article.RenameTitleInput;
import com.example.scenarios.inbound.article.RenameTitleInPort;
import com.example.scenarios.outbound.article.ArticleExtractorOutPort;
import com.example.scenarios.outbound.article.ArticleUpdaterOutPort;
import jakarta.inject.Named;

@Named
class RenameTitleUseCase implements RenameTitleInPort {

  private final ArticleExtractorOutPort articleExtractorOutPort;
  private final ArticleUpdaterOutPort articleUpdaterOutPort;

  RenameTitleUseCase(ArticleExtractorOutPort articleExtractorOutPort, ArticleUpdaterOutPort articleUpdaterOutPort) {
    this.articleExtractorOutPort = articleExtractorOutPort;
    this.articleUpdaterOutPort = articleUpdaterOutPort;
  }

  @Override
  public void execute(RenameTitleInput input) {

    final var articleId = new ArticleId(input.articleId());
    final var article = articleExtractorOutPort.get(articleId);
    final var title = new Title(input.title());

    final var command = new RenameTitleCommand(articleId, title);

    articleUpdaterOutPort.update(article.renameTitle(command));
  }
}
