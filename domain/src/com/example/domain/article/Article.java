package com.example.domain.article;

import com.example.common.types.AggregateRoot;
import com.example.common.types.Version;
import com.example.common.utilities.CollectionsUtils;
import com.example.domain.article.commands.PostArticleCommand;
import com.example.domain.article.events.ArticlePostedEvent;
import com.example.domain.article.events.ParagraphAddedEvent;
import com.example.domain.category.Category;
import com.example.domain.category.CategoryId;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Article extends AggregateRoot<ArticleId> {

  private Title title;
  private  CategoryId categoryId;
  private final List<Paragraph> paragraphs;
  private Rating rating;
  private final Instant publishedAt;
  private Instant updatedAt;
  private Instant deletedAt;
  private ArticleStatus status;

  public Article(
    ArticleId articleId,
    Title title,
    List<Paragraph> paragraphs,
    Rating rating,
    Version version,
    CategoryId categoryId,
    Instant publishedAt,
    Instant updatedAt,
    Instant deletedAt,
    ArticleStatus status
  ) {
    super(articleId, version);
    this.title = title;
    this.paragraphs = paragraphs;
    this.rating = rating;
    this.categoryId = categoryId;
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

    final var article = new Article(
      command.articleId(),
      title,
      new ArrayList<>(),
      Rating.newRating(),
      Version.newVersion(),
      command.categoryId(),
      Instant.now(),
      null,
      null,
      ArticleStatus.PUBLISHED
    );
    article.addEvent(ArticlePostedEvent.create(article.id.asLongValue(), article.publishedAt));
    article.addParagraphs(command.paragraphs(), paragraphIdProvider);
    return article;
  }


  private void addParagraphs(List<String> paragraphs, ParagraphIdProvider provider) {
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
    for (String text : filteredParagraphs) {
      Paragraph paragraph = Paragraph.createNew(id, provider.provide(), text);
      addParagraph(paragraph);
    }
  }

  private void addParagraph(Paragraph paragraph) {
    paragraphs.add(paragraph);
    addEvent(ParagraphAddedEvent.create(paragraph.articleId.asLongValue(), paragraph.id.asLongValue(), paragraph.text()));
  }

  public Title title() {
    return title;
  }

  public List<Paragraph> paragraphs() {
    return List.copyOf(paragraphs);
  }

  public Rating rating() {
    return rating;
  }

  public Instant publishedAt() {
    return publishedAt;
  }

  public Instant updatedAt() {
    return updatedAt;
  }

  public Instant deletedAt() {
    return deletedAt;
  }

  public ArticleStatus status() {
    return status;
  }

  public CategoryId categoryId() {
    return categoryId;
  }
}
