package com.lib.net.exception;

public final class ApiException extends Exception {
  private final int code;
  private final String message;

  public ApiException(Throwable cause, int code, String message) {
    super(cause);
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  @Override public String getMessage() {
    return message;
  }
}
