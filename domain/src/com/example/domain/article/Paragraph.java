package com.example.domain.article;

import com.example.common.types.DomainEntity;
import com.example.common.types.Version;
import com.example.common.utilities.CollectionsUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

  public static List<Paragraph> create(
    ArticleId articleId,
    List<String> paragraphs,
    ParagraphIdProvider provider
  ) {
    if (Objects.isNull(paragraphs)) {
      throw new IllegalStateException("Article should contain at least one Paragraph");
    }

    final List<String> filteredParagraphs = CollectionsUtils.streamOf(paragraphs)
      .filter(Objects::nonNull)
      .filter(text -> !text.isBlank())
      .toList();

    if (filteredParagraphs.isEmpty()) {
      throw new IllegalStateException("Article should contain at least one non empty Paragraph");
    }

    // one more invariant example
    if (filteredParagraphs.size() > 10) {
      throw new IllegalStateException("Article should not contain more then 10 paragraphs.");
    }

    return CollectionsUtils
      .streamOf(filteredParagraphs)
      .map(text -> Paragraph.createNew(articleId, provider.provide(), text))
      .collect(Collectors.toList());
  }

  public String text() {
    return text;
  }

  void changeText(String text) {
    this.text = text;
  }
}
