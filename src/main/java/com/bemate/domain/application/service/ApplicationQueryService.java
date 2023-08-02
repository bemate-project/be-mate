package com.bemate.domain.application.service;

import com.bemate.domain.application.endpoint.response.ApplicationQueryResponse;
import com.bemate.domain.application.endpoint.response.ApplicationUserDto;
import com.bemate.domain.application.endpoint.response.dto.ApplicationDto;
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

    public List<ApplicationQueryResponse> findByUser(User user) {
        var applications = applicationRepository.findByUser(user)
                .orElseThrow(() -> ApplicationNotFoundException.byUserNo(user.getId()));
        return applications.stream()
                .map(ApplicationQueryResponse::from)
                .toList();
    }

    public List<ApplicationQueryResponse> findByPet(Pet pet) {
        var applications = applicationRepository.findByPet(pet)
                .orElseThrow(() -> ApplicationNotFoundException.byPetKey(pet.getId()));
        var applicationUsers = applications.stream()
                .map(ApplicationUserDto::from)
                .toList();

        return applicationUsers.stream()
                .map(ApplicationQueryResponse::from)
                .toList();
    }

    public ApplicationDto findById(Long applicationNo) {
        var application = applicationRepository.findById(applicationNo)
                .orElseThrow(() -> ApplicationNotFoundException.byId(applicationNo));

        return ApplicationDto.from(application);
    }
}
