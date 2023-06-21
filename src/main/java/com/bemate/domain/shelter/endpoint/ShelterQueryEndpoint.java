package com.bemate.domain.shelter.endpoint;

import com.bemate.domain.shelter.service.ShelterQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShelterQueryEndpoint {

    private final ShelterQueryService shelterQueryService;

    @GetMapping("/shelters")
    public ResponseEntity<List<String>> getShelters() {
        return ResponseEntity.ok(shelterQueryService.getAllShelters());
    }
}
