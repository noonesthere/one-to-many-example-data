package com.example.scenarios.article;

import com.example.domain.article.Article;
import com.example.domain.article.commands.ChangeCategoryCommand;
import com.example.scenarios.dto.article.ChangeCategoryInput;
import com.example.scenarios.inbound.article.ChangeCategoryInPort;
import com.example.scenarios.outbound.article.ArticleExtractorOutPort;
import com.example.scenarios.outbound.article.ArticleUpdaterOutPort;
import jakarta.inject.Named;

@Named
class ChangeCategoryUseCase implements ChangeCategoryInPort {

  private final ArticleUpdaterOutPort articleUpdaterOutPort;
  private final ArticleExtractorOutPort extractor;

  ChangeCategoryUseCase(ArticleUpdaterOutPort articleUpdaterOutPort, ArticleExtractorOutPort extractor) {
    this.articleUpdaterOutPort = articleUpdaterOutPort;
    this.extractor = extractor;
  }

  @Override
  public void execute(ChangeCategoryInput input) {
    final ChangeCategoryCommand command = input.toCommand();
    final Article article = extractor.get(command.articleId());
    articleUpdaterOutPort.update(article.changeCategory(command));
  }
}
