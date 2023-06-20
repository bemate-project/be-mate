package com.bemate.domain.shelter.repository;

import com.bemate.domain.shelter.entity.ShelterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterUserRepository extends JpaRepository<ShelterUser, Long> {
}
