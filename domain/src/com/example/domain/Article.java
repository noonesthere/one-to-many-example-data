package com.example.domain;

import com.example.common.types.AggregateRoot;
import com.example.domain.commands.PostArticleCommand;

public class Article extends AggregateRoot<ArticleId> {
  public static Article post(PostArticleCommand postArticleCommand) {

    return null;
  }
}
