package com.bootios.alone.domain.post.exception;

public class NotFondPostEntityException extends RuntimeException {
  public NotFondPostEntityException() {
    super("Post 를 찾을 수 없음");
  }
}
