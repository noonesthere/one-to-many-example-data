package com.example.rest.article;

import com.example.scenarios.dto.article.EditParagraphInput;
import io.hypersistence.tsid.TSID;
import org.springframework.util.Assert;

import java.util.Objects;

public record EditParagraphWebModel(
  String articleId,
  String paragraphId,
  String text
) {
  EditParagraphInput to() {
    return new EditParagraphInput(
      TSID.from(articleId).toLong(),
      TSID.from(paragraphId).toLong(),
      text
    );
  }

}
