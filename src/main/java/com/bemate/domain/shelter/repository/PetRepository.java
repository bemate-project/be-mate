package com.bemate.domain.shelter.repository;

import com.bemate.domain.shelter.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, String>, PetRepositoryCustom {
    Page<Pet> findByShelterNo(Long heroNo, Pageable pageable);
}
