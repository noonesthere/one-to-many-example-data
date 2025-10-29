package com.example.scenarios.inbound.article;

import com.example.domain.article.ArticleId;
import com.example.scenarios.dto.article.ArticleInput;

public interface PostArticleInPort {

  ArticleId execute(ArticleInput articleInput);
}
