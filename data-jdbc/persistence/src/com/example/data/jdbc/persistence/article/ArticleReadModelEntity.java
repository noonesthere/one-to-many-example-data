package com.example.data.jdbc.persistence.article;

import org.springframework.data.relational.core.mapping.Column;

public record ArticleReadModelEntity(
  @Column("ID") Long id,
  @Column("TITLE") String title,
  @Column("RATING") Double rating,
  @Column("NAME") String categoryName
) {
}
