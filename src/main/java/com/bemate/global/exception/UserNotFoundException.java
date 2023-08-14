package com.bemate.global.exception;

import com.bemate.global.exception.handler.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    private UserNotFoundException(String msg) {
        super(msg);
    }

    public static UserNotFoundException byEmail(String email) {
        return new UserNotFoundException(String.format("User is not found - email: %s", email));
    }

    public static UserNotFoundException byId(Long id) {
        return new UserNotFoundException(String.format("User is not found - id: %d", id));
    }
}
