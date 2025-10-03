package com.example.scenarios.outbound;

import com.example.domain.Article;

public interface ArticlePersister {
  void persist(Article article);
}
