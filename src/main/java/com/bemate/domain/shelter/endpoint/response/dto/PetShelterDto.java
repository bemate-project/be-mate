package com.bemate.domain.shelter.endpoint.response.dto;

import com.bemate.domain.shelter.AdoptionStatus;
import com.bemate.domain.shelter.Gender;
import com.bemate.domain.shelter.HealthStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class PetShelterDto {
    private String petKey;
    private String species;
    private String kind;
    private int age;
    private Gender gender;
    private String characteristics;
    private HealthStatus healthStatus;
    private String imageFolder;
    private String imageFiles;
    private AdoptionStatus adoptionStatus;
    private Long shelterNo;
    private String shelterName;
    private String email;
    private String zipCode;
    private String streetAddress;
    private String detailAddress;

    @QueryProjection
    public PetShelterDto(String petKey, String species, String kind, int age, Gender gender, String characteristics, HealthStatus healthStatus, String imageFolder, String imageFiles, AdoptionStatus adoptionStatus, Long shelterNo, String shelterName, String email, String zipCode, String streetAddress, String detailAddress) {
        this.petKey = petKey;
        this.species = species;
        this.kind = kind;
        this.age = age;
        this.gender = gender;
        this.characteristics = characteristics;
        this.healthStatus = healthStatus;
        this.imageFolder = imageFolder;
        this.imageFiles = imageFiles;
        this.adoptionStatus = adoptionStatus;
        this.shelterNo = shelterNo;
        this.shelterName = shelterName;
        this.email = email;
        this.zipCode = zipCode;
        this.streetAddress = streetAddress;
        this.detailAddress = detailAddress;
    }
}
