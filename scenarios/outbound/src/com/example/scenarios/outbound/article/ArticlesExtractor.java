package com.example.scenarios.outbound.article;

import com.example.domain.article.Article;

import java.nio.channels.FileChannel;
import java.util.List;

public interface ArticlesExtractor {
  List<Article> findAll();
}
