package com.bemate.domain.shelter.repository;

import com.bemate.domain.shelter.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, String>, PetRepositoryCustom {
}
