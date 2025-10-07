package com.example.scenarios.outbound.article;

import com.example.domain.article.Article;
import com.example.domain.article.ArticleId;

public interface ArticleExtractor {
  Article get(ArticleId id);
}
