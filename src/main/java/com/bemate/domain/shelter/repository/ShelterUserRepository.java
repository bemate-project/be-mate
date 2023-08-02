package com.bemate.domain.shelter.repository;

import com.bemate.domain.shelter.entity.Shelter;
import com.bemate.domain.shelter.entity.ShelterUser;
import com.bemate.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShelterUserRepository extends JpaRepository<ShelterUser, Long> {
    @Query("select t from ShelterUser t join fetch t.shelter")
    Optional<List<ShelterUser>> findByUser(User user);

    @Query("select t from ShelterUser t left join fetch t.user")
    Optional<ShelterUser> findByShelter(Shelter shelter);
}
