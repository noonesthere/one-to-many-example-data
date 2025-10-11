package com.example.scenarios.article;

import com.example.domain.article.ArticleId;
import com.example.domain.article.Title;
import com.example.domain.article.commands.RenameTitleCommand;
import com.example.scenarios.dto.article.RenameTitleInput;
import com.example.scenarios.inbound.article.RenameTitle;
import com.example.scenarios.outbound.article.ArticleExtractor;
import com.example.scenarios.outbound.article.ArticlePersister;
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

    final var articleId = new ArticleId(input.articleId());
    final var article = articleExtractor.get(articleId);
    final var title = new Title(input.title());

    final var command = new RenameTitleCommand(articleId, title);

    persister.persist(article.renameTitle(command));
  }
}
