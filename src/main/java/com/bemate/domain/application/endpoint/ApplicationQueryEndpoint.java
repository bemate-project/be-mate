package com.bemate.domain.application.endpoint;

import com.bemate.domain.application.endpoint.response.ApplicationQueryResponse;
import com.bemate.domain.application.endpoint.response.dto.ApplicationDto;
import com.bemate.domain.application.service.ApplicationQueryService;
import com.bemate.domain.shelter.service.PetQueryService;
import com.bemate.domain.user.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicationQueryEndpoint {

    private final ApplicationQueryService applicationQueryService;
    private final UserQueryService userQueryService;
    private final PetQueryService petQueryService;

    @GetMapping("/applications/user/{id}")
    public ResponseEntity<List<ApplicationQueryResponse>> findApplicationsByUser(@PathVariable(value = "id") Long userNo) {
        return ResponseEntity.ok(
                applicationQueryService.findByUser(userQueryService.findById(userNo))
        );
    }

    @GetMapping("/applications/pet/{id}")
    public ResponseEntity<List<ApplicationQueryResponse>> findApplicationsByPet(@PathVariable("id") String petKey) {
        return ResponseEntity.ok(
                applicationQueryService.findByPet(petQueryService.findById(petKey))
        );
    }

    @GetMapping("/applications/{id}")
    public ResponseEntity<ApplicationDto> findApplication(@PathVariable(value = "id") Long applicationNo) {
        return ResponseEntity.ok(applicationQueryService.findById(applicationNo));
    }
}
