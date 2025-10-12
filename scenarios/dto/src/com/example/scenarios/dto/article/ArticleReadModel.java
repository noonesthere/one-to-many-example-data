package com.example.scenarios.dto.article;

import java.util.List;

public record ArticleReadModel(
  Long id,
  String title,
  List<String> paragraphs,
  Double rating,
  String categoryName
) {
}
