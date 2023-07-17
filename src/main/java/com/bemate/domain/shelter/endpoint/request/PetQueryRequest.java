package com.bemate.domain.shelter.endpoint.request;

import com.bemate.domain.shelter.AdoptionStatus;
import lombok.Data;

@Data
public class PetQueryRequest {
    private String species;
    private String kind;
    private AdoptionStatus adoptionStatus;
}
