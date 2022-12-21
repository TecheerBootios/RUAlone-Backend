package com.bootios.alone.domain.post.exception;

import com.bootios.alone.global.error.ErrorCode;
import com.bootios.alone.global.error.exception.BusinessException;

public class NotFondPostEntityException extends BusinessException {
  public NotFondPostEntityException() {
    super(ErrorCode.NOT_FOUND_POST_ENTITY);
  }
}
