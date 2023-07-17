package com.bemate.domain.shelter.endpoint;

import com.bemate.domain.shelter.endpoint.request.PetQueryRequest;
import com.bemate.domain.shelter.endpoint.response.dto.PetShelterDto;
import com.bemate.domain.shelter.service.PetQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PetQueryEndpoint {

    private final PetQueryService petQueryService;

    @GetMapping("/shelters/pet")
    public Page<PetShelterDto> searchPets(PetQueryRequest request, Pageable pageable) {
        return petQueryService.searchPage(request, pageable);
    }
}
