package com.example.scenarios.article;

import com.example.domain.article.Article;
import com.example.domain.article.commands.ChangeCategoryCommand;
import com.example.scenarios.dto.article.ChangeCategoryInput;
import com.example.scenarios.inbound.article.ChangeCategoryInPort;
import com.example.scenarios.outbound.article.ArticleExtractor;
import com.example.scenarios.outbound.article.ArticleUpdater;
import jakarta.inject.Named;

@Named
class ChangeCategoryUseCase implements ChangeCategoryInPort {

  private final ArticleUpdater articleUpdater;
  private final ArticleExtractor extractor;

  ChangeCategoryUseCase(ArticleUpdater articleUpdater, ArticleExtractor extractor) {
    this.articleUpdater = articleUpdater;
    this.extractor = extractor;
  }

  @Override
  public void execute(ChangeCategoryInput input) {
    final ChangeCategoryCommand command = input.toCommand();
    final Article article = extractor.get(command.articleId());
    articleUpdater.update(article.changeCategory(command));
  }
}
