package com.bemate.domain.application.endpoint.request;

import com.bemate.domain.application.HomeConditions;
import com.bemate.domain.application.entity.Application;
import com.bemate.domain.shelter.entity.Pet;
import com.bemate.domain.user.entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ApplicationWriteRequest {
    @NotBlank
    private String petKey;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String streetAddress;
    @NotBlank
    private String detailAddress;
    @Enumerated(EnumType.STRING)
    private HomeConditions homeConditions;

    public Application toApplication(User user, Pet pet) {
        return Application.builder()
                .user(user)
                .pet(pet)
                .zipCode(zipCode)
                .streetAddress(streetAddress)
                .detailAddress(detailAddress)
                .homeConditions(homeConditions)
                .build();
    }
}
