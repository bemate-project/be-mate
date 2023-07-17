package com.bemate.global.exception.handler;

public abstract class MethodArgumentNotValidException extends RuntimeException {
    public MethodArgumentNotValidException(String msg) {
        super(msg);
    }
}