package com.bemate.domain.shelter.repository;

import com.bemate.domain.shelter.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetWriteRepository extends JpaRepository<Pet, String> {
}
