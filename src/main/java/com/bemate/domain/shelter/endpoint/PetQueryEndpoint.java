package com.bemate.domain.shelter.endpoint;

import com.bemate.domain.shelter.endpoint.request.PetQueryRequest;
import com.bemate.domain.shelter.endpoint.response.dto.PetDto;
import com.bemate.domain.shelter.endpoint.response.dto.PetShelterDto;
import com.bemate.domain.shelter.service.PetQueryService;
import com.bemate.domain.shelter.service.ShelterQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Pet")
public class PetQueryEndpoint {

    private final PetQueryService petQueryService;
    private final ShelterQueryService shelterQueryService;

    @GetMapping("/shelters/pet")
    @Operation(summary = "Retrieve Pets")
    public ResponseEntity<Page<PetShelterDto>> findPets(PetQueryRequest request, Pageable pageable) {
        return ResponseEntity.ok(petQueryService.findByCondition(request, pageable));
    }

    @GetMapping("/shelters/{id}/pet")
    @Operation(summary = "Retrieve Pets By ShelterId")
    public ResponseEntity<Page<PetDto>> findPetsByShelterNo(@PathVariable(value = "id") Long shelterNo, Pageable pageable) {
       return ResponseEntity.ok(petQueryService.findByShelterNo(shelterQueryService.findById(shelterNo), pageable));
    }
}
