package com.example.scenarios.inbound.article;

import com.example.scenarios.dto.article.ArticleInput;

public interface PostArticle {

  void execute(ArticleInput articleInput);
}
