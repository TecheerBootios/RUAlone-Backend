package com.bootios.alone.domain.post.exception;

public class CNotFoundPostEntityException extends RuntimeException {
  public CNotFoundPostEntityException() {
    super();
  }

  public CNotFoundPostEntityException(String message) {
    super(message);
  }

  public CNotFoundPostEntityException(String message, Throwable cause) {
    super(message, cause);
  }
}
