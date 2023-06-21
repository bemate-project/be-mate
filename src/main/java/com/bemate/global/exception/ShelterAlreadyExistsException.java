package com.bemate.global.exception;

import com.bemate.global.exception.handler.AlreadyExistsException;

public class ShelterAlreadyExistsException extends AlreadyExistsException {
    public ShelterAlreadyExistsException(String name) {
        super(String.format("Shelter already exists - shelterName: %s", name));
    }
}
