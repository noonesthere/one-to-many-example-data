package com.example.rest.article;

import com.example.common.utilities.CollectionsUtils;
import com.example.scenarios.dto.article.ArticleReadModel;
import io.hypersistence.tsid.TSID;

import java.util.List;

public record GetArticleWebModel(
  String id,
  String title,
  List<String> paragraphs,
  Double rating,
  String categoryName
) {
  public static List<GetArticleWebModel> from(List<ArticleReadModel> dtos) {
    return CollectionsUtils.streamOf(dtos).map(GetArticleWebModel::from).toList();
  }

  public static GetArticleWebModel from(ArticleReadModel dto) {
    return new GetArticleWebModel(
      TSID.from(dto.id()).toString(),
      dto.title(),
      dto.paragraphs(),
      dto.rating(),
      dto.categoryName()
    );
  }
}
