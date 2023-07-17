package com.bemate.global.exception;

import com.bemate.global.exception.handler.MethodArgumentNotValidException;

public class ShelterNoNotValidException extends MethodArgumentNotValidException {
    public ShelterNoNotValidException(Long id) {
        super(String.format("shelterNo is not valid - shelterNo: %d", id));
    }
}