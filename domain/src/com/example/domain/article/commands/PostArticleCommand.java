package com.example.domain.article.commands;

import com.example.domain.article.ArticleId;
import com.example.domain.category.CategoryId;

import java.util.List;

public record PostArticleCommand(
  ArticleId articleId,
  String title,
  List<String> paragraphs,
  CategoryId categoryId
) {
}
