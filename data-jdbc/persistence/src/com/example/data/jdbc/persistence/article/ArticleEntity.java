package com.example.data.jdbc.persistence.article;

import com.example.common.types.Version;
import com.example.common.utilities.CollectionsUtils;
import com.example.domain.article.Article;
import com.example.domain.article.ArticleId;
import com.example.domain.article.ArticleStatus;
import com.example.domain.article.Paragraph;
import com.example.domain.article.Rating;
import com.example.domain.article.Title;
import com.example.domain.category.CategoryId;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Table("ARTICLE")
public record ArticleEntity(
  @Id @Column("ID") Long id,
  @Column("TITLE") String title,
  @Column("CATEGORY_ID") Long categoryId,
  @MappedCollection(idColumn = "ARTICLE_ID") List<ParagraphEntity> paragraphs,
  @Column("RATING") Double rating,
  @Column("VOTE_COUNT") Integer voteCount,
  @Column("PUBLISHED_AT") Instant publishedAt,
  @Column("UPDATED_AT") Instant updatedAt,
  @Column("DELETED_AT") Instant deletedAt,
  @Column("STATUS") int status,
  @Column("VERSION") @org.springframework.data.annotation.Version Long version
) implements Persistable<Long> {

  static ArticleEntity from(Article article) {
    return new ArticleEntity(
      article.id.value(),
      article.title().asStringValue(),
      article.categoryId().value(),
      map(article.paragraphs()),
      article.rating().value(),
      article.rating().count(),
      article.publishedAt(),
      article.updatedAt(),
      article.deletedAt(),
      article.status().id,
      article.version().value() - 1
    );
  }

  Article to() {
    return new Article(
      new ArticleId(id),
      new Title(title),
      Rating.from(rating, voteCount),
      Version.from(version),
      new CategoryId(categoryId),
      publishedAt,
      updatedAt,
      deletedAt,
      ArticleStatus.from(status),
      (a) -> CollectionsUtils.streamOf(paragraphs)
        .map(ParagraphEntity::to)
        .collect(Collectors.toList())
    );
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public boolean isNew() {
    return Objects.isNull(updatedAt);
  }

  private static List<ParagraphEntity> map(List<Paragraph> paragraphs) {
    return paragraphs.stream()
      .map(ParagraphEntity::from)
      .toList();
  }
}
