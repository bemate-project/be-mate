package com.bemate.global.exception;

import com.bemate.global.exception.handler.NotFoundException;

public class ApplicationNotFoundException extends NotFoundException {
    public ApplicationNotFoundException(Long id) {
        super(String.format(String.format("Application is not found - userNo: %s", id)));
    }
}
