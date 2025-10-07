package com.example.rest.article;

import com.example.scenarios.dto.article.RenameTitleInput;
import io.hypersistence.tsid.TSID;

public record RenameArticleWebModel(
  String articleId,
  String title
) {
  RenameTitleInput to() {
    return new RenameTitleInput(
      TSID.from(articleId).toLong(),
      title
    );
  }
}
