package com.example.scenarios.article;

import com.example.domain.article.ArticleId;
import com.example.domain.article.ParagraphId;
import com.example.domain.article.commands.EditParagraphCommand;
import com.example.scenarios.dto.article.EditParagraphInput;
import com.example.scenarios.inbound.article.EditParagraph;
import com.example.scenarios.outbound.article.ArticlePersister;
import com.example.scenarios.outbound.article.ArticleExtractor;
import jakarta.inject.Named;

@Named
class EditParagraphUseCase implements EditParagraph {

  private final ArticleExtractor extractor;
  private final ArticlePersister persister;

  EditParagraphUseCase(
    ArticleExtractor extractor,
    ArticlePersister persister
  ) {
    this.extractor = extractor;
    this.persister = persister;
  }

  @Override
  public void execute(EditParagraphInput input) {
    final var id = new ArticleId(input.articleId());
    final var paragraphId = new ParagraphId(input.paragraphId());

    final var article = extractor.get(id);

    article.editParagraph(new EditParagraphCommand(id, paragraphId, input.text()));

    persister.persist(article);
  }
}
