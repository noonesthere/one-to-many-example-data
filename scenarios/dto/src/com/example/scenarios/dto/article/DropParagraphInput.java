package com.example.scenarios.dto.article;

import com.example.domain.article.ArticleId;
import com.example.domain.article.ParagraphId;
import com.example.domain.article.commands.DropParagraphCommand;

public record DropParagraphInput(Long articleId, Long paragraphId) {
  public DropParagraphCommand toCommand() {
    return new DropParagraphCommand(
      new ArticleId(articleId),
      new ParagraphId(paragraphId)
    );
  }
}
