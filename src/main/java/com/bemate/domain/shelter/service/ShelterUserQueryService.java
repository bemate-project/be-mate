package com.bemate.domain.shelter.service;

import com.bemate.domain.shelter.entity.ShelterUser;
import com.bemate.domain.shelter.repository.ShelterUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterUserQueryService {

    private final ShelterUserRepository shelterUserRepository;

    public List<ShelterUser> getShelterJoin() {
        return shelterUserRepository.findShelterUserFetchJoin();
    }
}
