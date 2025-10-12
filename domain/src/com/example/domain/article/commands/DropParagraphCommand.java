package com.example.domain.article.commands;

import com.example.domain.article.ArticleId;
import com.example.domain.article.ParagraphId;

public record DropParagraphCommand(
  ArticleId articleId,
  ParagraphId paragraphId
) {
}
