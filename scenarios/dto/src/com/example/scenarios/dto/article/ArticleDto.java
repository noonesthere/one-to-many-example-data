package com.example.scenarios.dto.article;

import com.example.domain.article.Article;
import com.example.domain.article.Paragraph;

import java.util.List;

public record ArticleDto(
  String title,
  List<String> paragraphs,
  Double rating
) {

  public static ArticleDto from(Article article) {
    List<String> paragraphs = article.paragraphs().stream().map(Paragraph::text).toList();
    return new ArticleDto(
      article.title().asStringValue(),
      paragraphs,
      article.rating().asDoubleValue()
    );
  }
}
