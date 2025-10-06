package com.example.domain.article.commands;

import com.example.domain.article.ArticleId;
import com.example.domain.category.Category;

import java.util.List;

public record PostArticleCommand(
  ArticleId articleId,
  String title,
  List<String> paragraphs,
  Category category
) {
}
