package com.bemate.global.exception;

import com.bemate.global.exception.handler.NotFoundException;

public class PetNotFoundException extends NotFoundException {
    public PetNotFoundException(String id) {
        super(String.format("Pet is not found - id: %s", id));
    }
}
