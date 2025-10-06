package com.example.domain.article.exceptions;

import com.example.common.types.BusinessException;

public abstract class ArticleException extends BusinessException {
  protected ArticleException(String message) {
    super(message);
  }
}
