package com.example.rest.article;

import com.example.scenarios.dto.article.VoteArticleInput;
import io.hypersistence.tsid.TSID;

public record VoteArticleWebModel(
  String articleId,
  Integer grade
) {
  VoteArticleInput to() {
    return new VoteArticleInput(
      TSID.from(articleId).toLong(),
      grade
    );
  }

}
