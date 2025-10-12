package com.example.scenarios.outbound.article;

import com.example.scenarios.dto.article.ArticleReadModel;

import java.util.List;

public interface ArticlesExtractor {
  List<ArticleReadModel> findAll();
}
