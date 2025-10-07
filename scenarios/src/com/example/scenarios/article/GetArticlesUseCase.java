package com.example.scenarios.article;

import com.example.common.utilities.CollectionsUtils;
import com.example.scenarios.dto.article.ArticleDto;
import com.example.scenarios.inbound.article.GetArticles;
import com.example.scenarios.outbound.article.ArticlesExtractor;
import jakarta.inject.Named;

import java.util.List;

@Named
class GetArticlesUseCase implements GetArticles {

  private final ArticlesExtractor extractor;

  GetArticlesUseCase(ArticlesExtractor extractor) {
    this.extractor = extractor;
  }

  @Override
  public List<ArticleDto> execute() {
    return CollectionsUtils.streamOf(extractor.findAll())
      .map(ArticleDto::from)
      .toList();
  }
}
