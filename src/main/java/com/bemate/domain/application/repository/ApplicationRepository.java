package com.bemate.domain.application.repository;

import com.bemate.domain.application.entity.Application;
import com.bemate.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Query("select a from Application a join fetch a.pet")
    Optional<List<Application>> findByUser(User user);
}
