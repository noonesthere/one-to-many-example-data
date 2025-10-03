package com.example.domain.exceptions;

import com.example.common.types.BusinessException;

public abstract class ArticleException extends BusinessException {
  protected ArticleException(String message) {
    super(message);
  }
}
