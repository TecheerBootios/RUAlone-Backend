package com.bootios.alone.domain.users.domain.exception;

public class NotFoundUserEntityException extends RuntimeException {
  public NotFoundUserEntityException() {
    super("User 를 찾을 수 없음");
  }
}
