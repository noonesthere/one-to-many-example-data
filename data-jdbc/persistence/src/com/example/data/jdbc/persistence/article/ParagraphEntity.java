package com.example.data.jdbc.persistence.article;

import com.example.common.types.Version;
import com.example.domain.article.ArticleId;
import com.example.domain.article.Paragraph;
import com.example.domain.article.ParagraphId;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("PARAGRAPH")
public record ParagraphEntity(
  @Column("ARTICLE_ID") Long articleId,
  @Column("ID") Long id,
  @Column("TEXT") String text,
  @Column("VERSION")Long version
) {
  static ParagraphEntity from(Paragraph paragraph) {
    return new ParagraphEntity(
      paragraph.articleId.value(),
      paragraph.id().value(),
      paragraph.text(),
      paragraph.version().value() - 1
    );
  }

  public Paragraph to() {
    return new Paragraph(
      new ParagraphId(id),
      Version.from(version),
      new ArticleId(articleId),
      text
    );
  }
}
