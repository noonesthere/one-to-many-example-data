package com.example.data.jdbc.persistence.article;

import com.example.common.types.Version;
import com.example.common.utilities.CollectionsUtils;
import com.example.domain.article.*;
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
      article.id.asLongValue(),
      article.title().asStringValue(),
      article.categoryId().asLongValue(),
      map(article.paragraphs()),
      article.rating().value(),
      article.rating().count(),
      article.publishedAt(),
      article.updatedAt(),
      article.deletedAt(),
      article.status().id,
      article.version().asLongValue() - 1
    );
  }

  Article to() {
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
