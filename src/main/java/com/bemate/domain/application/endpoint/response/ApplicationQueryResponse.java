package com.bemate.domain.application.endpoint.response;

import com.bemate.domain.application.endpoint.response.dto.ApplicationDto;
import com.bemate.domain.application.entity.Application;
import com.bemate.domain.shelter.endpoint.response.dto.PetDto;
import com.bemate.domain.shelter.endpoint.response.dto.ShelterDto;
import com.bemate.domain.user.endpoint.response.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApplicationQueryResponse {
    private ApplicationDto application;
    private PetDto pet;
    private ShelterDto shelter;
    private UserDto user;

    public static ApplicationQueryResponse from(Application application) {
        var pet = application.getPet();
        return ApplicationQueryResponse.builder()
                .application(ApplicationDto.from(application))
                .pet(PetDto.from(pet))
                .shelter(ShelterDto.from(pet.getShelter()))
                .build();
    }

    public static ApplicationQueryResponse from(ApplicationUserDto applicationUserDto) {
        return ApplicationQueryResponse.builder()
                .application(applicationUserDto.getApplication())
                .user(applicationUserDto.getUser())
                .build();
    }
}
