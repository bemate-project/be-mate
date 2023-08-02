package com.bemate.domain.application.endpoint.response.dto;

import com.bemate.domain.application.ApplicationStatus;
import com.bemate.domain.application.HomeConditions;
import com.bemate.domain.application.entity.Application;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationDto {
    private Long applicationNo;
    private String zipCode;
    private String streetAddress;
    private String detailAddress;
    private HomeConditions homeConditions;
    private ApplicationStatus applicationStatus;

    public static ApplicationDto from(Application application) {
        return ApplicationDto.builder()
                .applicationNo(application.getId())
                .zipCode(application.getZipCode())
                .streetAddress(application.getStreetAddress())
                .detailAddress(application.getDetailAddress())
                .homeConditions(application.getHomeConditions())
                .applicationStatus(application.getApplicationStatus())
                .build();
    }
}
