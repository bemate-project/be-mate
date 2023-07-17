package com.bemate.domain.shelter.endpoint.request;

import com.bemate.domain.shelter.AdoptionStatus;
import com.bemate.domain.shelter.Gender;
import com.bemate.domain.shelter.HealthStatus;
import com.bemate.domain.shelter.entity.Pet;
import com.bemate.domain.shelter.entity.Shelter;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PetWriteRequest {
    @NotBlank
    private String species;
    @NotBlank
    private String kind;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String characteristics;
    @Enumerated(EnumType.STRING)
    private HealthStatus healthStatus;
    @Enumerated(EnumType.STRING)
    private AdoptionStatus adoptionStatus;

    public Pet toPet(Shelter shelter) {
        return Pet.builder()
                .id(generatePetId(shelter.getId()))
                .shelter(shelter)
                .species(species)
                .kind(kind)
                .age(age)
                .gender(gender)
                .characteristics(characteristics)
                .healthStatus(healthStatus)
                .adoptionStatus(adoptionStatus)
                .build();
    }

    private String generatePetId(Long shelterNo) {
        var now = LocalDateTime.now();
        return shelterNo.toString() + "-" + now.getHour() + now.getMinute() + now.getSecond();
    }
}
