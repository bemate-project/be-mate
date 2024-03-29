package com.bemate.domain.shelter.endpoint;

import com.bemate.domain.shelter.endpoint.request.ShelterApiRequest;
import com.bemate.domain.shelter.service.ShelterQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Shelter")
public class ShelterQueryEndpoint {

    private final ShelterQueryService shelterQueryService;

    @GetMapping("/shelters")
    public ResponseEntity<List<String>> findShelters(@RequestParam("stateProvinceCd") String stateProvinceCd,
                                                    @RequestParam("cityCd") String cityCd) {
        return ResponseEntity.ok(shelterQueryService.getAllShelters(
                ShelterApiRequest.builder()
                        .stateProvinceCode(stateProvinceCd)
                        .cityCode(cityCd)
                        .build()
        ));
    }
}
