package com.bemate.domain.application.service;

import com.bemate.domain.application.endpoint.response.ApplicationPetDto;
import com.bemate.domain.application.repository.ApplicationRepository;
import com.bemate.domain.user.entity.User;
import com.bemate.global.exception.ApplicationNotFoundException;
import com.bemate.global.exception.handler.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationQueryService {

    private final ApplicationRepository applicationRepository;

    public List<ApplicationPetDto> getPetJoin(User user) {
        return applicationRepository.findByUser(user)
                .orElseThrow(() -> new ApplicationNotFoundException(user.getId());
    }
}
