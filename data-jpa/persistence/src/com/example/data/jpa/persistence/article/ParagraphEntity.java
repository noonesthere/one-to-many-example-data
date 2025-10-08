package com.example.data.jpa.persistence.article;


import com.example.common.types.Version;
import com.example.domain.article.ArticleId;
import com.example.domain.article.Paragraph;
import com.example.domain.article.ParagraphId;
import jakarta.persistence.*;

@Entity
@Table(name = "PARAGRAPH")
public class ParagraphEntity {

  @Id
  @Column(name = "ID", nullable = false, updatable = false)
  private Long id;

  @JoinColumn(name = "ARTICLE_ID", nullable = false)
  private Long articleId;

  @Column(name = "TEXT", nullable = false)
  private String text;

  @jakarta.persistence.Version
  @Column(name = "VERSION")
  private Long version;

  protected ParagraphEntity() {
  }

  public ParagraphEntity(Long articleId, Long id, String text, Long version) {
    this.articleId = articleId;
    this.id = id;
    this.text = text;
    this.version = version;
  }

  public static ParagraphEntity from(Paragraph paragraph) {
    ParagraphEntity entity = new ParagraphEntity();
    entity.id = paragraph.id.asLongValue();
    entity.text = paragraph.text();
    entity.version = paragraph.version().value() - 1;
    return entity;
  }

  public Paragraph to() {
    return new Paragraph(
      new ParagraphId(id),
      Version.from(version),
      ArticleId.from(articleId),
      text
    );
  }

  public void setArticleId(Long articleId) {
    this.articleId = articleId;
  }
}
