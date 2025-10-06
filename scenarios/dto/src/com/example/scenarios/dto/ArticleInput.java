package com.example.scenarios.dto;

import java.util.List;

public record ArticleInput(
  String title,
  List<String> paragraphs,
  Long categoryId
) {
}
