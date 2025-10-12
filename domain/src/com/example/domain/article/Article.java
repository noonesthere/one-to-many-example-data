package com.example.domain.article;

import com.example.common.types.AggregateRoot;
import com.example.common.types.Version;
import com.example.domain.article.commands.ChangeCategoryCommand;
import com.example.domain.article.commands.DropParagraphCommand;
import com.example.domain.article.commands.EditParagraphCommand;
import com.example.domain.article.commands.PostArticleCommand;
import com.example.domain.article.commands.RenameTitleCommand;
import com.example.domain.article.commands.VoteCommand;
import com.example.domain.article.events.ArticlePostedEvent;
import com.example.domain.article.events.ArticleRateChangedEvent;
import com.example.domain.article.events.ArticleTitleRenamedEvent;
import com.example.domain.article.events.CategoryChangedEvent;
import com.example.domain.article.events.ParagraphAddedEvent;
import com.example.domain.article.events.ParagraphEditedEvent;
import com.example.domain.article.events.ParagraphRemovedEvent;
import com.example.domain.category.CategoryId;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Article extends AggregateRoot<ArticleId> {

  private Title title;
  private CategoryId categoryId;
  private final List<Paragraph> paragraphs;
  private Rating rating;
  private final Instant publishedAt;
  private Instant deletedAt;
  private ArticleStatus status;

  public Article(
    ArticleId articleId,
    Title title,
    Rating rating,
    Version version,
    CategoryId categoryId,
    Instant publishedAt,
    Instant deletedAt,
    ArticleStatus status,
    ParagraphsSupplier paragraphsSupplier
  ) {
    super(articleId, version);
    // TODO: add invariants
    this.title = Objects.requireNonNull(title);
    this.rating = Objects.requireNonNull(rating);
    this.categoryId = Objects.requireNonNull(categoryId);
    this.publishedAt = Objects.requireNonNull(publishedAt);
    this.deletedAt = deletedAt;
    this.status = Objects.requireNonNull(status);
    this.paragraphs = Objects.requireNonNull(Objects.requireNonNull(paragraphsSupplier).apply(this));
  }

  public static Article post(PostArticleCommand command) {
    return new Article(
      command.articleId(),
      command.title(),
      Rating.newRating(),
      Version.newVersion(),
      command.categoryId(),
      Instant.now(),
      null,
      ArticleStatus.PUBLISHED,
      (a) -> {
        a.addEvent(ArticlePostedEvent.create(a.id.value(), a.publishedAt, a.categoryId.value()));

        final List<Paragraph> ps = new ArrayList<>();
        for (Paragraph p : command.paragraphs()) {
          ps.add(p);
          a.addEvent(
            ParagraphAddedEvent.create(a.id.value(), p.id.value(), p.text())
          );
        }

        return ps;
      }
    );
  }

  public void vote(VoteCommand command) {
    rating = rating.addVote(command.grade());
    addEvent(
      ArticleRateChangedEvent.create(id, rating, version())
    );
  }

  public Article renameTitle(RenameTitleCommand command) {
    final var articleTitleRenamedEvent = ArticleTitleRenamedEvent.create(
      command.articleId(),
      command.title(),
      version()
    );
    addEvent(articleTitleRenamedEvent);
    return this;
  }

  public Article changeCategory(ChangeCategoryCommand command) {
    categoryId = command.categoryId();
    addEvent(CategoryChangedEvent.create(id, categoryId, version()));
    return this;
  }

  public void editParagraph(EditParagraphCommand command) {
    final String text = command.text();

    if (Objects.isNull(text) || text.isBlank()) {
      throw new IllegalStateException("Paragraph can not be empty");
    }

    findParagraph(command.paragraphId())
      .ifPresent(p -> changeText(p, text));
  }

  private void changeText(Paragraph p, String text) {
    if (p.changeText(text)) {
      addEvent(ParagraphEditedEvent.create(id, p.id, text, version()));
    }
  }

  public void dropParagraph(DropParagraphCommand command) {
    findParagraph(command.paragraphId())
      .ifPresent(this::removeParagraph);
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

  public Instant deletedAt() {
    return deletedAt;
  }

  public ArticleStatus status() {
    return status;
  }

  public CategoryId categoryId() {
    return categoryId;
  }

  private void removeParagraph(Paragraph paragraph) {
    this.paragraphs.remove(paragraph);
    addEvent(ParagraphRemovedEvent.create(id, paragraph.id, version()));
  }

  private Optional<Paragraph> findParagraph(ParagraphId paragraphId) {
    return paragraphs.stream().filter(p -> p.id.equals(paragraphId)).findFirst();
  }
}
