package com.example.domain.exceptions;

import com.example.common.types.BusinessException;

public abstract class CommentException extends BusinessException {
  protected CommentException(String message) {
    super(message);
  }
}
