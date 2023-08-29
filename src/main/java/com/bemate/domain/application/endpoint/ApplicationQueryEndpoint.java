package com.bemate.domain.application.endpoint;

import com.bemate.domain.application.endpoint.response.ApplicationQueryResponse;
import com.bemate.domain.application.endpoint.response.dto.ApplicationDto;
import com.bemate.domain.application.service.ApplicationQueryService;
import com.bemate.domain.shelter.service.PetQueryService;
import com.bemate.domain.user.service.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Application")
public class ApplicationQueryEndpoint {

    private final ApplicationQueryService applicationQueryService;
    private final UserQueryService userQueryService;
    private final PetQueryService petQueryService;

    @GetMapping("/applications/user/{id}")
    @Operation(summary = "Retrieve Applications By UserId")
    public ResponseEntity<List<ApplicationQueryResponse>> findApplicationsByUser(@PathVariable(value = "id") Long userNo) {
        return ResponseEntity.ok(
                applicationQueryService.findByUser(userQueryService.findById(userNo))
        );
    }

    @GetMapping("/applications/pet/{id}")
    @Operation(summary = "Retrieve Applications By PetId")
    public ResponseEntity<List<ApplicationQueryResponse>> findApplicationsByPet(@PathVariable("id") String petKey) {
        return ResponseEntity.ok(
                applicationQueryService.findByPet(petQueryService.findById(petKey))
        );
    }

    @GetMapping("/applications/{id}")
    @Operation(summary = "Retrieve Application Details")
    public ResponseEntity<ApplicationDto> findApplication(@PathVariable(value = "id") Long applicationNo) {
        return ResponseEntity.ok(applicationQueryService.findById(applicationNo));
    }
}
