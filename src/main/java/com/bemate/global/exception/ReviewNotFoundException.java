package com.bemate.global.exception;

import com.bemate.global.exception.handler.NotFoundException;

public class ReviewNotFoundException extends NotFoundException {
    public ReviewNotFoundException(Long id) {
        super(String.format("Review is not found - id: %d", id));
    }
}
