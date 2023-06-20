package com.bemate.domain.shelter.service;

import com.bemate.domain.shelter.entity.Shelter;
import com.bemate.domain.shelter.repository.ShelterRepository;
import com.bemate.global.exception.ShelterAlreadyExistsException;
import com.bemate.global.exception.handler.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShelterWriteService {

    private final ShelterRepository shelterRepository;

    public Shelter register(Shelter shelter) {
        if(isAlreadyExists(shelter.getShelterName())) {
            throw new ShelterAlreadyExistsException(shelter.getShelterName());
        }

        return shelterRepository.save(shelter);
    }

    private boolean isAlreadyExists(String name) {
        try {
            shelterRepository.findByShelterName(name);
        } catch (NotFoundException e) {
            return false;
        }
        return true;
    }
}
