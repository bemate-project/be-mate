package com.bemate.domain.shelter.endpoint.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShelterApiRequest {
    @NotBlank
    private String stateProvinceCode;
    @NotBlank
    private String cityCode;
}
