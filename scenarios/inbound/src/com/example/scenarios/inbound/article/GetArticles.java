package com.example.scenarios.inbound.article;

import com.example.scenarios.dto.article.ArticleReadModel;

import java.util.List;

public interface GetArticles {
  List<ArticleReadModel> execute();
}
