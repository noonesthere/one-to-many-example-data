package com.example.domain.article;

import com.example.common.types.AggregateRoot;
import com.example.common.types.Version;
import com.example.common.utilities.CollectionsUtils;
import com.example.domain.article.commands.PostArticleCommand;
import com.example.domain.article.events.ArticlePostedEvent;
import com.example.domain.category.Category;

import java.lang.module.ModuleDescriptor;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class Article extends AggregateRoot<ArticleId> {

  private final Title title;
  private final Category category;
  private final List<Paragraph> paragraphs;
  private final Rating rating;
  private final Instant publishedAt;
  private final Instant updatedAt;
  private final Instant deletedAt;
  private final ArticleStatus status;

  public Article(
    ArticleId articleId,
    Title title,
    List<Paragraph> paragraphs,
    Rating rating,
    Version version,
    Category category,
    Instant publishedAt,
    Instant updatedAt,
    Instant deletedAt,
    ArticleStatus status
  ) {
    super(articleId, version);
    this.title = title;
    this.paragraphs = paragraphs;
    this.rating = rating;
    this.category = category;
    this.publishedAt = publishedAt;
    this.updatedAt = updatedAt;
    this.deletedAt = deletedAt;
    this.status = status;
  }

  public static Article post(
    PostArticleCommand command,
    ParagraphIdProvider paragraphIdProvider
  ) {

    final var title = new Title(command.title());
    final var paragraphs = map(command.articleId(), command.paragraphs(), paragraphIdProvider);

    final var article = new Article(
      command.articleId(),
      title,
      paragraphs,
      Rating.newRating(),
      Version.newVersion(),
      command.category(),
      Instant.now(),
      null,
      null,
      ArticleStatus.PUBLISHED
    );

    final List<Long> pids = paragraphs.stream()
      .map(p -> p.id().asLongValue())
      .toList();

    article.addEvent(new ArticlePostedEvent(article.id.asLong(), pids, article.publishedAt));
    return article;
  }

  private static List<Paragraph> map(ArticleId articleId, List<String> paragraphs, ParagraphIdProvider provider) {
    if (Objects.isNull(paragraphs)) {
      throw new IllegalStateException("Article should contain at least one Paragraph");
    }

    final List<String> filteredParagraphs = CollectionsUtils.streamOf(paragraphs)
      .filter(Objects::nonNull)
      .filter(String::isBlank).toList();

    if (filteredParagraphs.isEmpty()) {
      throw new IllegalStateException("Article should contain at least one non empty Paragraph");
    }

    return filteredParagraphs.stream()
      .map(text -> Paragraph.from(articleId, provider, text))
      .toList();
  }
}
