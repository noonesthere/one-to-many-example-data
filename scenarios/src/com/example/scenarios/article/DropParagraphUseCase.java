package com.example.scenarios.article;

import com.example.domain.article.Article;
import com.example.domain.article.commands.DropParagraphCommand;
import com.example.scenarios.dto.article.DropParagraphInput;
import com.example.scenarios.inbound.article.DropParagraph;
import com.example.scenarios.outbound.article.ArticleExtractor;
import com.example.scenarios.outbound.article.ArticleParagraphRemover;
import jakarta.inject.Named;

@Named
class DropParagraphUseCase implements DropParagraph {

  private final ArticleParagraphRemover articleParagraphRemover;
  private final ArticleExtractor articleExtractor;

  DropParagraphUseCase(
    ArticleParagraphRemover articleParagraphRemover,
    ArticleExtractor articleExtractor
  ) {
    this.articleParagraphRemover = articleParagraphRemover;
    this.articleExtractor = articleExtractor;
  }

  @Override
  public void execute(DropParagraphInput input) {
    final DropParagraphCommand command = input.toCommand();
    final Article article = articleExtractor.get(command.articleId());
    article.dropParagraph(command);
    articleParagraphRemover.remove(article);
  }
}
