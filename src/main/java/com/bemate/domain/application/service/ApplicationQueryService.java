package com.bemate.domain.application.service;

import com.bemate.domain.application.endpoint.response.ApplicationUserDto;
import com.bemate.domain.application.endpoint.response.ApplicationPetDto;
import com.bemate.domain.application.repository.ApplicationRepository;
import com.bemate.domain.shelter.entity.Pet;
import com.bemate.domain.user.entity.User;
import com.bemate.global.exception.ApplicationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationQueryService {

    private final ApplicationRepository applicationRepository;

    public List<ApplicationPetDto> getPetJoin(User user) {
        var applications = applicationRepository.findByUser(user)
                .orElseThrow(() -> ApplicationNotFoundException.byUserNo(user.getId()));
        return applications
                .stream()
                .map(ApplicationPetDto::from)
                .toList();
    }

    public List<ApplicationUserDto> findByPet(Pet pet) {
        var applications = applicationRepository.findByPet(pet)
                .orElseThrow(() -> ApplicationNotFoundException.byPetKey(pet.getId()));
        return applications
                .stream()
                .map(ApplicationUserDto::from)
                .toList();
    }
}
