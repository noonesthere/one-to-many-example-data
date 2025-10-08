package com.example.data.jpa.persistence.article;


import com.example.common.types.Version;
import com.example.common.utilities.CollectionsUtils;
import com.example.domain.article.Article;
import com.example.domain.article.ArticleId;
import com.example.domain.article.ArticleStatus;
import com.example.domain.article.Paragraph;
import com.example.domain.article.Rating;
import com.example.domain.article.Title;
import com.example.domain.category.CategoryId;
import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "ARTICLE")
public class ArticleEntity {

  @Id
  @Column(name = "ID")
  private Long id;

  @Column(name = "TITLE", nullable = false)
  private String title;

  @Column(name = "CATEGORY_ID", nullable = false)
  private Long categoryId;

  @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<ParagraphEntity> paragraphs;

  @Column(name = "RATING")
  private Double rating;

  @Column(name = "VOTE_COUNT")
  private Integer voteCount;

  @Column(name = "PUBLISHED_AT")
  private Instant publishedAt;

  @Nullable
  @Column(name = "UPDATED_AT")
  private Instant updatedAt;

  @Nullable
  @Column(name = "DELETED_AT")
  private Instant deletedAt;

  @Column(name = "STATUS")
  private int status;

  @jakarta.persistence.Version
  @Column(name = "VERSION")
  private Long version;

  protected ArticleEntity() {
  }

  public ArticleEntity(
    Long id,
    String title,
    Long categoryId,
    List<ParagraphEntity> paragraphs,
    Double rating,
    Integer voteCount,
    Instant publishedAt,
    Instant updatedAt,
    Instant deletedAt,
    int status,
    Long version
  ) {
    this.id = id;
    this.title = title;
    this.categoryId = categoryId;
    this.paragraphs = paragraphs;
    this.rating = rating;
    this.voteCount = voteCount;
    this.publishedAt = publishedAt;
    this.updatedAt = updatedAt;
    this.deletedAt = deletedAt;
    this.status = status;
    this.version = version;
  }

  public static ArticleEntity from(Article article) {
    final var paragraphEntities = map(article.paragraphs());

    return new ArticleEntity(
      article.id.asLongValue(),
      article.title().asStringValue(),
      article.categoryId().asLongValue(),
      paragraphEntities,
      article.rating().value(),
      article.rating().count(),
      article.publishedAt(),
      article.updatedAt(),
      article.deletedAt(),
      article.status().id,
      article.version().asLongValue() - 1
    );
  }

  public Article to() {
    return new Article(
      ArticleId.from(id),
      new Title(title),
      CollectionsUtils.streamOf(paragraphs).map(ParagraphEntity::to).collect(Collectors.toList()),
      Rating.from(rating, voteCount),
      Version.from(version),
      CategoryId.from(categoryId),
      publishedAt,
      updatedAt,
      deletedAt,
      ArticleStatus.from(status)
    );
  }

  private static List<ParagraphEntity> map(List<Paragraph> paragraphs) {
    return paragraphs.stream()
      .map(ParagraphEntity::from)
      .collect(Collectors.toList());
  }
}
