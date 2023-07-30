package com.bemate.domain.shelter.repository;

import com.bemate.domain.shelter.entity.Pet;
import com.bemate.domain.shelter.entity.Shelter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PetRepository extends JpaRepository<Pet, String>, PetRepositoryCustom {
    @Query("select p from Pet p join fetch p.shelter")
    Page<Pet> findByShelter(Shelter shelter, Pageable pageable);
}