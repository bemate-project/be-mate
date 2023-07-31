package com.bemate.domain.application.endpoint.response;

import com.bemate.domain.application.ApplicationStatus;
import com.bemate.domain.application.HomeConditions;
import com.bemate.domain.application.entity.Application;
import com.bemate.domain.shelter.endpoint.response.dto.PetDto;
import lombok.Data;

@Data
public class ApplicationPetDto {
    private Long applicationNo;
    private String zipCode;
    private String streetAddress;
    private String detailAddress;
    private HomeConditions homeConditions;
    private ApplicationStatus applicationStatus;
    private PetDto pet;
//    private String petKey;
//    private String species;
//    private String kind;
//    private int age;
//    private Gender gender;
//    private String characteristics;
//    private HealthStatus healthStatus;
//    private AdoptionStatus adoptionStatus;

    public ApplicationPetDto(Application application) {
        this.applicationNo = application.getId();
        this.zipCode = application.getZipCode();
        this.streetAddress = application.getStreetAddress();
        this.detailAddress = application.getDetailAddress();
        this.homeConditions = application.getHomeConditions();
        this.applicationStatus = application.getApplicationStatus();
        this.pet = new PetDto(application.getPet());
    }
}
