package com.example.domain.article.commands;

import com.example.domain.article.ArticleId;
import com.example.domain.category.CategoryId;

public record ChangeCategoryCommand(
  ArticleId articleId,
  CategoryId categoryId
) {
}
