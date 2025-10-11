package com.example.rest.article;

import com.example.scenarios.dto.article.ChangeCategoryInput;
import io.hypersistence.tsid.TSID;

public record ChangeCategoryWebModel(
  long articleId,
  long categoryId
) {
  ChangeCategoryInput to() {

    return new ChangeCategoryInput(
      TSID.from(articleId).toLong(),
      TSID.from(categoryId).toLong()
    );
  }
}
