package com.bemate.domain.application.endpoint.response;

import com.bemate.domain.application.ApplicationStatus;
import com.bemate.domain.application.HomeConditions;
import lombok.Data;

@Data
public class ApplicationPetDto {
    private Long applicationNo;
    private String zipCode;
    private String streetAddress;
    private String detailAddress;
    private HomeConditions homeConditions;
    private ApplicationStatus applicationStatus;
}
