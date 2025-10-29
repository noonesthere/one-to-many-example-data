package com.example.scenarios.dto.article;

public record ChangeCategoryInput(
  long articleId,
  long categoryId
) {
}
