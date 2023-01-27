package com.bootios.alone.domain.post.exception;

public class COnlyCreatorUpdatePostException extends RuntimeException {
  public COnlyCreatorUpdatePostException() {super();}

  public COnlyCreatorUpdatePostException(String message) { super(message);}

  public COnlyCreatorUpdatePostException(String message, Throwable cause) { super(message, cause);}
}
