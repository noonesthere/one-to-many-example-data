package com.example.scenarios.outbound.article;

import com.example.domain.article.Article;

public interface ArticleRateChangerOutPort {
  void changeRate(Article article);
}
