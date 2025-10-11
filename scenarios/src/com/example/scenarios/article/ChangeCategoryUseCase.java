package com.example.scenarios.article;

import com.example.domain.article.Article;
import com.example.domain.article.commands.ChangeCategoryCommand;
import com.example.scenarios.dto.article.ChangeCategoryInput;
import com.example.scenarios.inbound.article.ChangeCategory;
import com.example.scenarios.outbound.article.ArticleExtractor;
import com.example.scenarios.outbound.article.ArticlePersister;
import jakarta.inject.Named;

@Named
class ChangeCategoryUseCase implements ChangeCategory {

  private final ArticlePersister persister;
  private final ArticleExtractor extractor;

  ChangeCategoryUseCase(ArticlePersister persister, ArticleExtractor extractor) {
    this.persister = persister;
    this.extractor = extractor;
  }

  @Override
  public void execute(ChangeCategoryInput input) {
    final ChangeCategoryCommand command = input.toCommand();
    final Article article = extractor.get(command.articleId());
    persister.persist(article.changeCategory(command));
  }
}
