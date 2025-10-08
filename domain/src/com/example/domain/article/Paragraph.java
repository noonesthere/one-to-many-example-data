package com.example.domain.article;

import com.example.common.types.DomainEntity;
import com.example.common.types.Version;

public class Paragraph extends DomainEntity<ParagraphId> {

  public final ArticleId articleId;
  private String text;

  public Paragraph(ParagraphId id, Version version, ArticleId articleId, String text) {
    super(id, version);
    this.articleId = articleId;
    this.text = text;
  }

  static Paragraph createNew(ArticleId articleId, ParagraphId paragraphId, String text) {
    //validation for text e.g filter buzz words
    return new Paragraph(paragraphId, Version.newVersion(), articleId, text);
  }

  public String text() {
    return text;
  }

  void changeText(String text) {
    this.text = text;
  }
}
