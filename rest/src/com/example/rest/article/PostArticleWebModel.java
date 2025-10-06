package com.example.rest.article;

import com.example.scenarios.dto.ArticleInput;
import io.hypersistence.tsid.TSID;

import java.util.List;

public record PostArticleWebModel(
  String title,
  List<String> paragraphs,
  String categoryId
) {


  public ArticleInput to() {
    return new ArticleInput(
      title,
      paragraphs,
      TSID.from(categoryId).toLong()
    );
  }

}
