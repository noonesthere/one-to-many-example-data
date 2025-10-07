package com.example.scenarios.article;

import com.example.domain.article.ArticleId;
import com.example.domain.article.commands.RenameTitleCommand;
import com.example.scenarios.dto.article.RenameTitleInput;
import com.example.scenarios.inbound.article.RenameTitle;
import com.example.scenarios.outbound.ArticlePersister;
import com.example.scenarios.outbound.article.ArticleExtractor;
import jakarta.inject.Named;

@Named
class RenameTitleUseCase implements RenameTitle {

  private final ArticleExtractor articleExtractor;
  private final ArticlePersister persister;

  RenameTitleUseCase(ArticleExtractor articleExtractor, ArticlePersister persister) {
    this.articleExtractor = articleExtractor;
    this.persister = persister;
  }

  @Override
  public void execute(RenameTitleInput input) {
    final var id = ArticleId.from(input.articleId());
    final var article = articleExtractor.get(id);
    article.renameTitle(new RenameTitleCommand(input.title()));

    persister.persist(article);
  }
}
