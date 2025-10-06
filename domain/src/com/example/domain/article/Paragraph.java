package com.example.domain.article;

import com.example.common.types.DomainEntity;
import com.example.common.types.Version;

public class Paragraph extends DomainEntity<ParagraphId> {

  private final ArticleId articleId;
  private final String text;

  protected Paragraph(ParagraphId id, Version version, ArticleId articleId, String text) {
    super(id, version);
    this.articleId = articleId;
    this.text = text;
  }

  public static Paragraph from(ArticleId articleId, ParagraphIdProvider provider, String text) {
    return new Paragraph(provider.provide(), Version.newVersion(), articleId, text);
  }
}
