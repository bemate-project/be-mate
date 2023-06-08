package com.bemate.global.exception;

import com.bemate.global.exception.handler.UnauthorizedException;

public class TokenTypeMismatchException extends UnauthorizedException {

    private TokenTypeMismatchException(String msg) {
        super(msg);
    }

    public static TokenTypeMismatchException refreshTokenExpected(String token) {
        return new TokenTypeMismatchException(String.format("Token: %s is not a refresh token.", token));
    }

    public static TokenTypeMismatchException accessTokenExpected(String token) {
        return new TokenTypeMismatchException(String.format("Token: %s is not an access token.", token));
    }
}
