package com.example.scenarios.article;

import com.example.domain.article.Article;
import com.example.domain.article.ArticleId;
import com.example.domain.article.ArticleIdProvider;
import com.example.domain.article.Paragraph;
import com.example.domain.article.ParagraphIdProvider;
import com.example.domain.article.Title;
import com.example.domain.article.commands.PostArticleCommand;
import com.example.domain.category.CategoryId;
import com.example.scenarios.dto.article.ArticleInput;
import com.example.scenarios.inbound.article.PostArticle;
import com.example.scenarios.outbound.article.ArticlePersister;
import com.example.scenarios.outbound.category.CategoryExtractor;
import jakarta.inject.Named;

import java.util.List;

@Named
class PostArticleUseCase implements PostArticle {

  private final ArticlePersister articlePersister;
  private final ArticleIdProvider articleIdProvider;
  private final CategoryExtractor categoryExtractor;
  private final ParagraphIdProvider paragraphIdProvider;

  public PostArticleUseCase(
    ArticlePersister articlePersister,
    ArticleIdProvider articleIdProvider,
    CategoryExtractor categoryExtractor, ParagraphIdProvider paragraphIdProvider
  ) {
    this.articlePersister = articlePersister;
    this.articleIdProvider = articleIdProvider;
    this.categoryExtractor = categoryExtractor;
    this.paragraphIdProvider = paragraphIdProvider;
  }

  @Override
  public ArticleId execute(ArticleInput articleInput) {

    final var articleId = articleIdProvider.provide();
    final var categoryId = new CategoryId(articleInput.categoryId());
    final var title = new Title(articleInput.title());

    categoryExtractor.get(categoryId); // TODO: just stub for emulate invariant

    final List<Paragraph> paragraphs = Paragraph.create(articleId, articleInput.paragraphs(), paragraphIdProvider);

    final var command = new PostArticleCommand(articleId, title, paragraphs, categoryId);

    final var article = Article.post(command);

    articlePersister.persist(article);

    return article.id;
  }
}
