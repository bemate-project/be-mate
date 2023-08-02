package com.bemate.global.exception;

import com.bemate.global.exception.handler.NotFoundException;

public class ShelterNotFoundException extends NotFoundException {

    private ShelterNotFoundException(String msg) {
        super(msg);
    }

    public static ShelterNotFoundException byName(String name) {
        return new ShelterNotFoundException(String.format("Shelter is not found - shelterName: %s", name));
    }

    public static ShelterNotFoundException byId(Long id) {
        return new ShelterNotFoundException(String.format("Shelter is not found - shelterNo: %d", id));
    }

    public static ShelterNotFoundException byUserNo(Long id) {
        return new ShelterNotFoundException(String.format("Shelter is not found - userNo: %d", id));
    }

    public static ShelterNotFoundException byPet(Long id) {
        return new ShelterNotFoundException(String.format("Shelter is not found - userNo: %d", id));
    }
}
