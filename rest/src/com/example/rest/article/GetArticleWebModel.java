package com.example.rest.article;

import com.example.common.utilities.CollectionsUtils;
import com.example.scenarios.dto.article.ArticleDto;
import io.hypersistence.tsid.TSID;

import java.util.List;

public record GetArticleWebModel(
  String id,
  String title,
  List<String> paragraphs,
  Double rating
) {
  public static List<GetArticleWebModel> from(List<ArticleDto> dtos) {
    return CollectionsUtils.streamOf(dtos).map(GetArticleWebModel::from).toList();
  }

  public static GetArticleWebModel from(ArticleDto dto) {
    return new GetArticleWebModel(
      TSID.from(dto.id()).toString(),
      dto.title(),
      dto.paragraphs(),
      dto.rating()
    );
  }
}
