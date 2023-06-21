package com.bemate.domain.shelter.endpoint;

import com.bemate.domain.auth.Principal;
import com.bemate.domain.shelter.endpoint.request.ShelterWriteRequest;
import com.bemate.domain.shelter.entity.Shelter;
import com.bemate.domain.shelter.entity.ShelterUser;
import com.bemate.domain.shelter.service.ShelterUserWriteService;
import com.bemate.domain.shelter.service.ShelterWriteService;
import com.bemate.domain.user.service.UserQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShelterEndpoint {
  
    private final ShelterWriteService shelterWriteService;
    private final ShelterUserWriteService shelterUserWriteService;
    private final UserQueryService userQueryService;

    @PostMapping("/shelters")
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
