package com.bemate.domain.shelter.repository;

import com.bemate.domain.shelter.entity.ShelterUser;
import com.bemate.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShelterUserRepository extends JpaRepository<ShelterUser, Long> {
    @Query("select t from ShelterUser t left join fetch t.shelter " +
            "where t.user = :user")
    Optional<List<ShelterUser>> findByUser(@Param("user") User user);

    @Query("select t from ShelterUser t " +
            "join fetch t.shelter s " +
            "join fetch t.user " +
            "where s.id = :id")
    Optional<ShelterUser> findByShelterNo(@Param("id") Long shelterNo);
}
