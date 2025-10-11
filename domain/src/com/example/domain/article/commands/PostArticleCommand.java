package com.example.domain.article.commands;

import com.example.domain.article.ArticleId;
import com.example.domain.article.Paragraph;
import com.example.domain.article.Title;
import com.example.domain.category.CategoryId;

import java.util.List;

public record PostArticleCommand(
  ArticleId articleId,
  Title title,
  List<Paragraph> paragraphs,
  CategoryId categoryId
) {
}
