package com.bemate.global.exception;

import com.bemate.global.exception.handler.UnauthorizedException;

public class PasswordMismatchException extends UnauthorizedException {
    public PasswordMismatchException() {
        super("Password does not match.");
    }
}
