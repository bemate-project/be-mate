package com.bemate.domain.shelter.repository;

import com.bemate.domain.shelter.entity.ShelterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShelterUserRepository extends JpaRepository<ShelterUser, Long> {
    @Query("select t from ShelterUser t left join fetch t.shelter "
            + "where t.id = :userNo")
    Optional<List<ShelterUser>> findShelterUserByUserNo(@Param("userNo") Long userNo);

    @Query("select t from ShelterUser t left join fetch t.user "
            + "where t.id = :shelterNo")
    Optional<ShelterUser> findShelterUserByShelterNo(@Param("shelterNo") Long shelterNo);
}
