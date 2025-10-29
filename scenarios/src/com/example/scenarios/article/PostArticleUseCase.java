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
import com.example.scenarios.inbound.article.PostArticleInPort;
import com.example.scenarios.outbound.article.ArticlePersisterOutPort;
import com.example.scenarios.outbound.category.CategoryExtractorOutPort;
import jakarta.inject.Named;

import java.util.List;

@Named
class PostArticleUseCase implements PostArticleInPort {

  private final ArticlePersisterOutPort articlePersisterOutPort;
  private final ArticleIdProvider articleIdProvider;
  private final CategoryExtractorOutPort categoryExtractorOutPort;
  private final ParagraphIdProvider paragraphIdProvider;

  public PostArticleUseCase(
    ArticlePersisterOutPort articlePersisterOutPort,
    ArticleIdProvider articleIdProvider,
    CategoryExtractorOutPort categoryExtractorOutPort, ParagraphIdProvider paragraphIdProvider
  ) {
    this.articlePersisterOutPort = articlePersisterOutPort;
    this.articleIdProvider = articleIdProvider;
    this.categoryExtractorOutPort = categoryExtractorOutPort;
    this.paragraphIdProvider = paragraphIdProvider;
  }

  @Override
  public ArticleId execute(ArticleInput articleInput) {

    final ArticleId articleId = articleIdProvider.provide();
    final var categoryId = new CategoryId(articleInput.categoryId());
    final var title = new Title(articleInput.title());

    categoryExtractorOutPort.get(categoryId); // TODO: just stub for emulate invariant

    final List<Paragraph> paragraphs = Paragraph.create(articleId, articleInput.paragraphs(), paragraphIdProvider);

    final var command = new PostArticleCommand(articleId, title, paragraphs, categoryId);

    final var article = Article.post(command);

    articlePersisterOutPort.persist(article);

    return article.id();
  }
}
