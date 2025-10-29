package com.example.scenarios.outbound.article;

import com.example.domain.article.Article;
import com.example.domain.article.ArticleId;

public interface ArticleExtractorOutPort {
  Article get(ArticleId id);
}
