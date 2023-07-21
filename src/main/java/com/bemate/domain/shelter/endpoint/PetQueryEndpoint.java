package com.bemate.domain.shelter.endpoint;

import com.bemate.domain.shelter.endpoint.request.PetQueryRequest;
import com.bemate.domain.shelter.endpoint.response.dto.PetDto;
import com.bemate.domain.shelter.endpoint.response.dto.PetShelterDto;
import com.bemate.domain.shelter.service.PetQueryService;
import com.bemate.domain.shelter.service.ShelterQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PetQueryEndpoint {

    private final PetQueryService petQueryService;
    private final ShelterQueryService shelterQueryService;

    @GetMapping("/shelters/pet")
    public Page<PetShelterDto> findPets(PetQueryRequest request, Pageable pageable) {
        return petQueryService.findByCondition(request, pageable);
    }

    @GetMapping("/shelters/{id}/pet")
    public Page<PetDto> findPetsByShelterNo(@PathVariable(value = "id") Long shelterNo, Pageable pageable) {
       return  petQueryService.findByShelterNo(shelterQueryService.findById(shelterNo), pageable);
    }
}
