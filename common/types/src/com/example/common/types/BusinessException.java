package com.example.common.types;

public abstract class BusinessException extends RuntimeException {
  protected BusinessException(String message) {
    super(message, null, true, false);
  }
}
