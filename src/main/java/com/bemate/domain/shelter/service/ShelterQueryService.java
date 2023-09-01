package com.bemate.domain.shelter.service;

import com.bemate.domain.shelter.entity.Shelter;
import com.bemate.domain.shelter.repository.ShelterRepository;
import com.bemate.global.exception.ShelterNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShelterQueryService {

    private final ShelterRepository shelterRepository;

    public Shelter findByName(String name) {
        return shelterRepository.findByShelterName(name)
                .orElseThrow(() -> ShelterNotFoundException.byName(name));
    }

    public Shelter findById(Long id) {
        return shelterRepository.findById(id)
                .orElseThrow(() -> ShelterNotFoundException.byId(id));
    }
}
