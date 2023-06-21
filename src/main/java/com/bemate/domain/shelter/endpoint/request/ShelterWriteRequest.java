package com.bemate.domain.shelter.endpoint.request;

import com.bemate.domain.shelter.entity.Shelter;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ShelterWriteRequest {
    @NotBlank
    private String shelterName;
    @NotBlank
    private String email;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String streetAddress;
    @NotBlank
    private String detailAddress;

    public Shelter toShelter() {
        return Shelter.builder()
                .shelterName(shelterName)
                .email(email)
                .zipCode(zipCode)
                .streetAddress(streetAddress)
                .detailAddress(detailAddress)
                .build();
    }
}
