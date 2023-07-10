package com.bemate.domain.shelter.endpoint;

import com.bemate.domain.auth.Principal;
import com.bemate.domain.shelter.endpoint.request.PetWriteRequest;
import com.bemate.domain.shelter.endpoint.request.ShelterWriteRequest;
import com.bemate.domain.shelter.entity.ShelterUser;
import com.bemate.domain.shelter.service.PetWriteService;
import com.bemate.domain.shelter.service.ShelterUserWriteService;
import com.bemate.domain.shelter.service.ShelterWriteService;
import com.bemate.domain.user.service.UserQueryService;
import com.bemate.domain.shelter.file.PetImageFile;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;

@RestController
@RequiredArgsConstructor
public class ShelterWriteEndpoint {
  
    private final ShelterWriteService shelterWriteService;
    private final PetWriteService petWriteService;
    private final ShelterUserWriteService shelterUserWriteService;
    private final UserQueryService userQueryService;

    @PostMapping("/shelters")
    public ResponseEntity registerShelter(@RequestBody @Valid ShelterWriteRequest shelterWriteRequest,
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

    @PostMapping("/shelters/{id}/pet")
    public ResponseEntity registerPet(@PathVariable(value = "id") Long shelterNo,
                                      @RequestPart(value = "petInfo") @Valid PetWriteRequest petWriteRequest,
                                      @RequestPart(value = "images", required = false) List<MultipartFile> multiImages) throws IOException {
        var pet = petWriteRequest.toPet(shelterNo);

        List<PetImageFile> images = multiImages == null
                ? emptyList()
                : multiImages.stream()
                .map(image -> new PetImageFile(pet, image))
                .toList();

        petWriteService.save(pet, images);

        return new ResponseEntity(HttpStatus.OK);
    }
}
