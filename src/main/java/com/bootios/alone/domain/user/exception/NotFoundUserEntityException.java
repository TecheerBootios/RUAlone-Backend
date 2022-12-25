package com.bootios.alone.domain.user.exception;

import com.bootios.alone.global.error.ErrorCode;
import com.bootios.alone.global.error.exception.BusinessException;

public class NotFoundUserEntityException extends BusinessException {
  public NotFoundUserEntityException() {
    super(ErrorCode.NOT_FOUND_USER_ENTITY);
  }
}
