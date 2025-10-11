package com.example.scenarios.dto.article;

import com.example.domain.article.ArticleId;
import com.example.domain.article.commands.ChangeCategoryCommand;
import com.example.domain.category.CategoryId;

public record ChangeCategoryInput(
  long articleId,
  long categoryId
) {

  public ChangeCategoryCommand toCommand() {
    return new ChangeCategoryCommand(
      new ArticleId(articleId),
      new CategoryId(categoryId)
    );

  }
}
