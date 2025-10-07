package com.example.scenarios.article;

import com.example.domain.article.ArticleId;
import com.example.domain.article.ArticleIdProvider;
import io.hypersistence.tsid.TSID;
import jakarta.inject.Named;

@Named
class ArticleTsIdProvider implements ArticleIdProvider {
  @Override
  public ArticleId provide() {
    return ArticleId.from(TSID.Factory.getTsid().toLong());
  }
}
