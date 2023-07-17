package com.bemate.domain.shelter.endpoint.response.dto;

import com.bemate.domain.shelter.entity.Shelter;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShelterDto {
    private String shelterName;

    public static ShelterDto from(Shelter shelter) {
        return ShelterDto.builder()
                .shelterName(shelter.getShelterName())
                .build();
    }
}
