package com.bootios.alone.domain.post.exception;

import com.bootios.alone.global.error.ErrorCode;
import com.bootios.alone.global.error.exception.BusinessException;

public class OnlyCreatorUpdatePostException extends BusinessException {
  public OnlyCreatorUpdatePostException() {
    super(ErrorCode.ONLY_CREATOR_UPDATE_POST);
  }
}
