package com.example.domain.article.exceptions;

import com.example.common.types.BusinessException;

public abstract class CommentException extends BusinessException {
  protected CommentException(String message) {
    super(message);
  }
}
