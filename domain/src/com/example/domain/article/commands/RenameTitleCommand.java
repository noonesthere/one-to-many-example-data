package com.example.domain.article.commands;

import com.example.domain.article.ArticleId;
import com.example.domain.article.Title;

public record RenameTitleCommand(
  ArticleId articleId,
  Title title
) {
}
