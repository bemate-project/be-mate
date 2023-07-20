package com.bemate.domain.shelter.endpoint.response.dto;

import com.bemate.domain.shelter.AdoptionStatus;
import com.bemate.domain.shelter.Gender;
import com.bemate.domain.shelter.HealthStatus;
import com.bemate.domain.shelter.entity.Pet;
import lombok.Data;

@Data
public class PetDto {
    private String id;
    private String species;
    private String kind;
    private int age;
    private Gender gender;
    private String characteristics;
    private HealthStatus healthStatus;
    private AdoptionStatus adoptionStatus;

    public PetDto(Pet pet) {
        this.id = pet.getId();
        this.species = pet.getSpecies();
        this.kind = pet.getKind();
        this.age = pet.getAge();
        this.gender = pet.getGender();
        this.characteristics = pet.getCharacteristics();
        this.healthStatus = pet.getHealthStatus();
        this.adoptionStatus = pet.getAdoptionStatus();
    }
}
