package com.example.scenarios.article;

import com.example.domain.article.Article;
import com.example.domain.article.ArticleId;
import com.example.domain.article.ParagraphId;
import com.example.domain.article.commands.DropParagraphCommand;
import com.example.scenarios.dto.article.DropParagraphInput;
import com.example.scenarios.inbound.article.DropParagraphInPort;
import com.example.scenarios.outbound.article.ArticleExtractorOutPort;
import com.example.scenarios.outbound.article.ArticleUpdaterOutPort;
import jakarta.inject.Named;

@Named
class DropParagraphUseCase implements DropParagraphInPort {

  private final ArticleUpdaterOutPort articleUpdaterOutPort;
  private final ArticleExtractorOutPort articleExtractorOutPort;

  DropParagraphUseCase(ArticleUpdaterOutPort articleUpdaterOutPort, ArticleExtractorOutPort articleExtractorOutPort) {
    this.articleUpdaterOutPort = articleUpdaterOutPort;
    this.articleExtractorOutPort = articleExtractorOutPort;
  }


  @Override
  public void execute(DropParagraphInput input) {
    final var articleId = new ArticleId(input.articleId());
    final var paragraphId = new ParagraphId(input.paragraphId());
    final var command = new DropParagraphCommand(articleId, paragraphId);
    
    final Article article = articleExtractorOutPort.get(command.articleId());

    article.dropParagraph(command);

    articleUpdaterOutPort.update(article);
  }
}
