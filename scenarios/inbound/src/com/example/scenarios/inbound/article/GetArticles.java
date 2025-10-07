package com.example.scenarios.inbound.article;

import com.example.scenarios.dto.article.ArticleDto;

import java.util.List;

public interface GetArticles {
  List<ArticleDto> execute();
}
