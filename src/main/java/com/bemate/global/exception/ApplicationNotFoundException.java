package com.bemate.global.exception;

import com.bemate.global.exception.handler.NotFoundException;

public class ApplicationNotFoundException extends NotFoundException {
    private ApplicationNotFoundException(String msg) {
        super(msg);
    }

    public static ApplicationNotFoundException byUserNo(Long id) {
        return new ApplicationNotFoundException(String.format(String.format("Application is not found - userNo: %d", id)));
    }

    public static ApplicationNotFoundException byPetKey(String id) {
        return new ApplicationNotFoundException(String.format(String.format("Application is not found - petKey: %s", id)));
    }

    public static ApplicationNotFoundException byId(Long id) {
        return new ApplicationNotFoundException(String.format(String.format("Application is not found - applicationNo: %s", id)));
    }
}
