package com.example.scenarios.dto.article;

public record EditParagraphInput(
  Long articleId,
  Long paragraphId,
  String text
) {
}
