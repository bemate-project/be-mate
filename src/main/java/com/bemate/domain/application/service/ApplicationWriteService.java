package com.bemate.domain.application.service;

import com.bemate.domain.application.ApplicationStatus;
import com.bemate.domain.application.entity.Application;
import com.bemate.domain.application.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationWriteService {

    private final ApplicationRepository applicationRepository;

    @Transactional
    public void submit(Application application) {
        var savedApplication = applicationRepository.save(application);

        if(savedApplication != null) {
            updateApplicationStatus(savedApplication, ApplicationStatus.SUBMITTED);
        }
    }

    @Transactional
    public void updateApplicationStatus(Application application, ApplicationStatus applicationStatus) {
        application.updateApplicationStatus(applicationStatus);
    }
}
