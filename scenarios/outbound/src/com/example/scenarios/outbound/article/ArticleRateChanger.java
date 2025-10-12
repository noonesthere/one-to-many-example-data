package com.example.scenarios.outbound.article;

import com.example.domain.article.Article;

public interface ArticleRateChanger {
  void changeRate(Article article);
}
