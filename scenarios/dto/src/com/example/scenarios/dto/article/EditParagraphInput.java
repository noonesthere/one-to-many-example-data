package com.example.scenarios.dto.article;

import com.example.domain.article.ArticleId;
import com.example.domain.article.ParagraphId;
import com.example.domain.article.commands.EditParagraphCommand;

public record EditParagraphInput(
  Long articleId,
  Long paragraphId,
  String text
) {

  public EditParagraphCommand asCommand() {
    final var id = new ArticleId(articleId());
    final var paragraphId = new ParagraphId(paragraphId());
    return new EditParagraphCommand(id, paragraphId, text());
  }
}
