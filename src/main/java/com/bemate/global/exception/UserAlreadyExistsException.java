package com.bemate.global.exception;

import com.bemate.global.exception.handler.AlreadyExistsException;

public class UserAlreadyExistsException extends AlreadyExistsException {
    public UserAlreadyExistsException(String email) {
        super(String.format("User already exists - email: %s", email));
    }
}
