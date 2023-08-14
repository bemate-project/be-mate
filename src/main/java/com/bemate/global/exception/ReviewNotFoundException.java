package com.bemate.global.exception;

import com.bemate.global.exception.handler.NotFoundException;

public class ReviewNotFoundException extends NotFoundException {

    private ReviewNotFoundException(String msg) {
        super(msg);
    }

    public static ReviewNotFoundException byId(Long id) {
        return new ReviewNotFoundException(String.format("Review is not found - id: %d", id));
    }

    public static ReviewNotFoundException byShelterNo(Long id) {
        return new ReviewNotFoundException(String.format("Review is not found - shelterNo: %d", id));
    }
}
