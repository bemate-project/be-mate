package com.bemate.global.exception.handler;

public abstract class ServerExecutionFailException extends RuntimeException {
  public ServerExecutionFailException(String message, Throwable cause) {
    super(message, cause);
  }
}