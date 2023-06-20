package com.bemate.domain.shelter.service;

import com.bemate.domain.shelter.entity.ShelterUser;
import com.bemate.domain.shelter.repository.ShelterUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShelterUserWriteService {

    private final ShelterUserRepository shelterUserRepository;

    public ShelterUser register(ShelterUser shelterUser) {
        return shelterUserRepository.save(shelterUser);
    }
}
