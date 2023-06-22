package com.bemate.domain.shelter.repository;

import com.bemate.domain.shelter.entity.ShelterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelterUserRepository extends JpaRepository<ShelterUser, Long> {
    @Query("select t from ShelterUser t left join fetch t.shelter")
    List<ShelterUser> findShelterUserFetchJoin();
}
