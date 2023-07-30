package com.bemate.domain.application.repository;

import com.bemate.domain.application.endpoint.response.ApplicationPetDto;
import com.bemate.domain.application.entity.Application;
import com.bemate.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<List<ApplicationPetDto>> findByUser(User user);
}
