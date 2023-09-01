package com.bemate.global.exception;

import com.bemate.global.exception.handler.ServerExecutionFailException;

public class S3UploadFailException extends ServerExecutionFailException {
  public S3UploadFailException(String fileName, Throwable cause) {
    super("S3 Upload Fail - fileName: " + fileName, cause);
  }
}
