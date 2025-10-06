package com.example.data.jdbc.persistence.article;

import com.example.domain.article.Article;
import com.example.domain.article.ArticleStatus;
import com.example.domain.article.Paragraph;
import com.example.domain.article.Rating;
import com.example.domain.article.Title;
import com.example.domain.category.Category;
import org.springframework.data.domain.Persistable;

import java.time.Instant;
import java.util.List;

public record ArticleEntity(
  Long id,
  String title,
  Long categoryId,
  List<ParagraphEntity> paragraphs,
  Double rating,
  Instant publishedAt,
  Instant updatedAt,
  Instant deletedAt,
  ArticleStatus status;

) implements Persistable<Long> {
  static ArticleEntity from(Article article) {
    return null;
  }

  @Override
  public Long getId() {
    return 0L;
  }

  @Override
  public boolean isNew() {
    return false;
  }
}
