package com.bemate.domain.application.endpoint.response;

import com.bemate.domain.application.ApplicationStatus;
import com.bemate.domain.application.HomeConditions;
import com.bemate.domain.application.entity.Application;
import com.bemate.domain.shelter.endpoint.response.dto.PetDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationPetDto {
    private Long applicationNo;
    private String zipCode;
    private String streetAddress;
    private String detailAddress;
    private HomeConditions homeConditions;
    private ApplicationStatus applicationStatus;
    private PetDto pet;

    public static ApplicationPetDto from(Application application) {
        return ApplicationPetDto.builder()
                .applicationNo(application.getId())
                .zipCode(application.getZipCode())
                .streetAddress(application.getStreetAddress())
                .detailAddress(application.getDetailAddress())
                .homeConditions(application.getHomeConditions())
                .applicationStatus(application.getApplicationStatus())
                .pet(PetDto.from(application.getPet()))
                .build();
    }
}
