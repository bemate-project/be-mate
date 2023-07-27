package com.bemate.domain.shelter.repository;

import com.bemate.domain.shelter.entity.Pet;
import com.bemate.domain.shelter.entity.Shelter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, String>, PetRepositoryCustom {
    Page<Pet> findByShelter(Shelter shelter, Pageable pageable);
}