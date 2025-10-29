package com.example.scenarios.outbound.article;

import com.example.domain.article.Article;

public interface ArticleCategoryChangerOutPort {
  void changeCategory(Article article);
}
