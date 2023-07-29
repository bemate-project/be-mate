package com.bemate.global.exception;

import com.bemate.global.exception.handler.UnauthorizedException;

public class UserMismatchException extends UnauthorizedException {
    public UserMismatchException(Long userNo) {
        super(String.format("User does not match - input userNo: %d", userNo));
    }
}
