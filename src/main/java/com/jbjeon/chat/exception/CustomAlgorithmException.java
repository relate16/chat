package com.jbjeon.chat.exception;

public class CustomAlgorithmException extends RuntimeException {
  public CustomAlgorithmException() {
    super();
  }

  public CustomAlgorithmException(String message) {
    super(message);
  }

  public CustomAlgorithmException(String message, Throwable cause) {
    super(message, cause);
  }

  public CustomAlgorithmException(Throwable cause) {
    super(cause);
  }

  protected CustomAlgorithmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
