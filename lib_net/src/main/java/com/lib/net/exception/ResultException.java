package com.lib.net.exception;

/**
 * 该异常是业务逻辑级别的异常，异常码是由服务端定义.
 */
public final class ResultException extends RuntimeException {
  /** 服务点定义的错误码. */
  private final int code;
  /** 服务端定义的错误信息. */
  private final String message;

  public ResultException(int code, String message) {
    super(message);
    this.code = code;
    this.message = message;
  }

  public int code() {
    return code;
  }

  public String message() {
    return message;
  }
}
