package com.bemate.domain.shelter.endpoint.response.dto;

import com.bemate.domain.shelter.AdoptionStatus;
import com.bemate.domain.shelter.Gender;
import com.bemate.domain.shelter.HealthStatus;
import lombok.Getter;

import java.util.List;

@Getter
public class PetDto {
    private String id;
    private String species;
    private String kind;
    private int age;
    private Gender gender;
    private String characteristics;
    private HealthStatus healthStatus;
    private List<String> images;
    private AdoptionStatus adoptionStatus;
}
