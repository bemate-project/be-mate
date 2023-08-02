package com.bemate.domain.shelter.endpoint.response.dto;

import com.bemate.domain.shelter.entity.Shelter;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShelterDto {
    private Long id;
    private String shelterName;

    public static ShelterDto from(Shelter shelter) {
        return ShelterDto.builder()
                .id(shelter.getId())
                .shelterName(shelter.getShelterName())
                .build();
    }
}
