package com.example.domain.article;

import com.example.common.types.AggregateRoot;
import com.example.common.types.Version;
import com.example.domain.category.Category;
import com.example.domain.article.commands.PostArticleCommand;

import java.time.Instant;
import java.util.List;

public class Article extends AggregateRoot<ArticleId> {

  private final Title title;
  private final Category category;
  private final List<Paragraph> paragraphs;
  private final Rating rating;
  private final Instant createdAt;
  private final Instant publishedAt;
  private final Instant updatedAt;
  private final Instant deletedAt;
  private final ArticleStatus status;

  public Article(
    ArticleId articleId,
    Title title,
    List<Paragraph> paragraphs,
    Rating rating,
    Version version, Category category, Instant createdAt,
    Instant publishedAt,
    Instant updatedAt,
    Instant deletedAt, ArticleStatus status
  ) {
    super(articleId, version);
    this.title = title;
    this.paragraphs = paragraphs;
    this.rating = rating;
    this.category = category;
    this.createdAt = createdAt;
    this.publishedAt = publishedAt;
    this.updatedAt = updatedAt;
    this.deletedAt = deletedAt;
    this.status = status;
  }

  public static Article post(PostArticleCommand postArticleCommand) {
    return null;
  }
}
