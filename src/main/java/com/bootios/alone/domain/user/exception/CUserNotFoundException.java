package com.bootios.alone.domain.user.exception;

public class CUserNotFoundException extends RuntimeException {

  public CUserNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public CUserNotFoundException(String message) {
    super(message);
  }

  public CUserNotFoundException() {
    super();
  }
}
