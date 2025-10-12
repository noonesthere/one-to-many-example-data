package com.example.data.jdbc.presentation.article;

import com.example.scenarios.dto.article.ArticleReadModel;
import io.hypersistence.tsid.TSID;

import java.util.List;

public record ArticleViewWebModel(
  String id,
  String title,
  List<String> paragraphs,
  Double rating,
  String categoryName

) {

  public static List<ArticleViewWebModel> from(List<ArticleReadModel> articles) {
    return articles.stream().map(ArticleViewWebModel::from).toList();
  }

  private static ArticleViewWebModel from(ArticleReadModel article) {
    return new ArticleViewWebModel(
      TSID.from(article.id()).toString(),
      article.title(),
      article.paragraphs(),
      article.rating(),
      article.categoryName()
    );
  }
}
