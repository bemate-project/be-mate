package com.bemate.domain.shelter.endpoint.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ShelterApiRequest {
    @NotBlank
    private String stateProvinceCode;
    @NotBlank
    private String cityCode;
}
