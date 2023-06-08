package com.bemate.global.exception.handler;

public abstract class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }
}
