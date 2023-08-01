package com.bemate.domain.shelter.endpoint.response.dto;

import com.bemate.domain.shelter.AdoptionStatus;
import com.bemate.domain.shelter.Gender;
import com.bemate.domain.shelter.HealthStatus;
import com.bemate.domain.shelter.entity.Pet;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PetDto {
    private String id;
    private String species;
    private String kind;
    private int age;
    private Gender gender;
    private String characteristics;
    private HealthStatus healthStatus;
    private AdoptionStatus adoptionStatus;

    public static PetDto from(Pet pet) {
        return PetDto.builder()
                .id(pet.getId())
                .species(pet.getSpecies())
                .kind(pet.getKind())
                .age(pet.getAge())
                .gender(pet.getGender())
                .characteristics(pet.getCharacteristics())
                .healthStatus(pet.getHealthStatus())
                .adoptionStatus(pet.getAdoptionStatus())
                .build();
    }
}
