package com.example.scenarios.outbound.article;

import com.example.domain.article.Article;

public interface ArticlePersister {
  void persist(Article article);
}
