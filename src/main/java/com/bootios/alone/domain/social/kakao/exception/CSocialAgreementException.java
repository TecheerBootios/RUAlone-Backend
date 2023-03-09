package com.bootios.alone.domain.social.kakao.exception;

public class CSocialAgreementException extends RuntimeException {
  public CSocialAgreementException() {
    super();
  }

  public CSocialAgreementException(String message) {
    super(message);
  }

  public CSocialAgreementException(String message, Throwable cause) {
    super(message, cause);
  }
}
