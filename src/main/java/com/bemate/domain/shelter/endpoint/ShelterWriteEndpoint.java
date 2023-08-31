package com.bemate.domain.shelter.endpoint;

import com.bemate.domain.auth.Principal;
import com.bemate.domain.shelter.endpoint.request.ShelterWriteRequest;
import com.bemate.domain.shelter.entity.ShelterUser;
import com.bemate.domain.shelter.service.*;
import com.bemate.domain.user.service.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Shelter")
public class ShelterWriteEndpoint {
  
    private final ShelterWriteService shelterWriteService;
    private final ShelterUserWriteService shelterUserWriteService;
    private final UserQueryService userQueryService;

    @PostMapping("/shelters")
    @Operation(summary = "Register Shelter")
    public ResponseEntity register(@RequestBody @Valid ShelterWriteRequest shelterWriteRequest,
                                          @AuthenticationPrincipal Principal principal) {
        var registeredShelter = shelterWriteService.register(shelterWriteRequest.toShelter());
        shelterUserWriteService.register(
                ShelterUser.builder()
                .user(userQueryService.findById(principal.getUserNo()))
                .shelter(registeredShelter)
                .build()
        );

        return new ResponseEntity(HttpStatus.OK);
    }
}
