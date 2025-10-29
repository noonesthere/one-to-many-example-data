package com.example.scenarios.outbound.article;

import com.example.domain.article.Article;

public interface ArticlePersisterOutPort {
  void persist(Article article);
}
