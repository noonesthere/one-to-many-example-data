package com.example.scenarios.article;

import com.example.domain.article.commands.EditParagraphCommand;
import com.example.scenarios.dto.article.EditParagraphInput;
import com.example.scenarios.inbound.article.EditParagraphInPort;
import com.example.scenarios.outbound.article.ArticleExtractorOutPort;
import com.example.scenarios.outbound.article.ArticleUpdaterOutPort;
import jakarta.inject.Named;

@Named
class EditParagraphUseCase implements EditParagraphInPort {

  private final ArticleExtractorOutPort articleExtractorOutPort;
  private final ArticleUpdaterOutPort articleUpdaterOutPort;

  EditParagraphUseCase(ArticleExtractorOutPort articleExtractorOutPort, ArticleUpdaterOutPort articleUpdaterOutPort) {
    this.articleExtractorOutPort = articleExtractorOutPort;
    this.articleUpdaterOutPort = articleUpdaterOutPort;
  }

  @Override
  public void execute(EditParagraphInput input) {
    final EditParagraphCommand command = input.asCommand();
    // TODO: can be different approach use single paragraph and then update article
    final var article = articleExtractorOutPort.get(command.articleId());
    article.editParagraph(command);
    articleUpdaterOutPort.update(article);
  }
}
