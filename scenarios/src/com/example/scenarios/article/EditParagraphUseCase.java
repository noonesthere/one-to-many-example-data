package com.example.scenarios.article;

import com.example.domain.article.commands.EditParagraphCommand;
import com.example.scenarios.dto.article.EditParagraphInput;
import com.example.scenarios.inbound.article.EditParagraphInPort;
import com.example.scenarios.outbound.article.ArticleExtractor;
import com.example.scenarios.outbound.article.ArticleUpdater;
import jakarta.inject.Named;

@Named
class EditParagraphUseCase implements EditParagraphInPort {

  private final ArticleExtractor articleExtractor;
  private final ArticleUpdater articleUpdater;

  EditParagraphUseCase(ArticleExtractor articleExtractor, ArticleUpdater articleUpdater) {
    this.articleExtractor = articleExtractor;
    this.articleUpdater = articleUpdater;
  }

  @Override
  public void execute(EditParagraphInput input) {
    final EditParagraphCommand command = input.asCommand();
    // TODO: can be different approach use single paragraph and then update article
    final var article = articleExtractor.get(command.articleId());
    article.editParagraph(command);
    articleUpdater.update(article);
  }
}
