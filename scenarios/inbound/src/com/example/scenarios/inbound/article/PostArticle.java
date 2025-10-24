package com.example.scenarios.inbound.article;

import com.example.domain.article.ArticleId;
import com.example.scenarios.dto.article.ArticleInput;

public interface PostArticle {

  ArticleId execute(ArticleInput articleInput);
}
