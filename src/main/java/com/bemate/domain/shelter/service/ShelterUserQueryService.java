package com.bemate.domain.shelter.service;

import com.bemate.domain.shelter.entity.ShelterUser;
import com.bemate.domain.shelter.repository.ShelterUserRepository;
import com.bemate.domain.user.entity.User;
import com.bemate.global.exception.ShelterNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterUserQueryService {

    private final ShelterUserRepository shelterUserRepository;
    private final ShelterQueryService shelterQueryService;

    public List<ShelterUser> findByUser(User user) {
        return shelterUserRepository.findByUser(user)
                .orElseThrow(() -> ShelterNotFoundException.byUserNo(user.getId()));
    }

    public ShelterUser findByShelter(Long shelterNo) {
        return shelterUserRepository.findByShelter(shelterQueryService.findById(shelterNo))
                .orElseThrow(() -> ShelterNotFoundException.byId(shelterNo));
    }
}
